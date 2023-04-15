package site.sammati.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.ActiveConsentDTO;
import site.sammati.dto.ConsentDataDTO;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;
import site.sammati.entity.ConsentRequest;
import site.sammati.repository.RegisteredHospitalRepository;
import site.sammati.service.ConsentDataService;
import site.sammati.service.ConsentRequestService;
import site.sammati.service.EncyDecryService;
import site.sammati.service.OTPService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static site.sammati.service.EncyDecryService.decryptData;
import static site.sammati.service.EncyDecryService.encryptData;
import static site.sammati.service.OTPService.generateOTP;
import static site.sammati.service.OTPService.sendOTP;
import static site.sammati.util.enums.ConsentRequestStatus.APPROVED;


@RestController
public class ConsentDataController {

    private static final Integer EXPIRE_MIN = 10;
    private static LoadingCache<Integer, ConsentDataDTO> consentDataCache;
    private static LoadingCache<Integer, String> keyCheckCache;
    private static LoadingCache<Integer, Map<Integer, String>> hospitalMapCache;
    private final Environment env;
    private final RegisteredHospitalRepository registeredHospitalRepository;
    private final ConsentDataService consentDataService;
    private final ConsentRequestService consentRequestService;

    public ConsentDataController(ConsentDataService consentDataService, RegisteredHospitalRepository registeredHospitalRepository, Environment env,ConsentRequestService consentRequestService) {
        this.consentDataService = consentDataService;
        this.registeredHospitalRepository = registeredHospitalRepository;
        this.env=env;
        this.consentRequestService=consentRequestService;
        consentDataCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, ConsentDataDTO>() {
                    @Override
                    public ConsentDataDTO load(Integer s) {
                        return null;
                    }
                });

        keyCheckCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer s) throws Exception {
                        return null;
                    }
                });

        hospitalMapCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, Map<Integer, String>>() {
                    @Override
                    public Map<Integer, String> load(Integer s) throws Exception {
                        return null;
                    }
                });
    }

    @PostMapping("/receive-consent-data")
    public Integer receiveConsentData(@RequestBody ConsentDataDTO consentDataDTO) throws ExecutionException {
        System.out.println(consentDataDTO);
        consentDataCache.put(consentDataDTO.getPatientId(), consentDataDTO);
        Integer patientId = consentDataDTO.getPatientId();
        String phoneNo = consentDataDTO.getPatientPhoneNo();
        Map<Integer, String> hospitalIps = new TreeMap<>();
        for(int i=0;i<consentDataDTO.getConsentDataMappingDTOList().size();i++){
            Integer hospitalID = consentDataDTO.getConsentDataMappingDTOList().get(i).getHospitalId();
            String ip = registeredHospitalRepository.getIpAddressByHospitalId(hospitalID);
            hospitalIps.put(hospitalID, ip);
        }
        hospitalMapCache.put(patientId, hospitalIps);
        String allKeys = "";
        for (Map.Entry<Integer, String> entry : hospitalIps.entrySet()) {
            System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
            String uri = "http://"+entry.getValue()+":"+env.getProperty("app.hospital_port")+"/authorize-patient?patientId="+patientId;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer "+registeredHospitalRepository.getTokenByHospitalId(entry.getKey()));
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Object.class);
//            RestTemplate restTemplate = new RestTemplate();
//            String result = restTemplate.getForObject(uri, String.class);
            allKeys+=result;
        }

        Integer otp = generateOTP();
        String myMessage = "Hello there, "+otp.toString()+ " is your OTP for ConsentRequestID: "+consentDataDTO.getConsentRequestId()+", Duration: "+consentDataDTO.getDuration()+" day/s.";
        System.out.println(allKeys);
        System.out.println(otp);
        keyCheckCache.put(patientId, encryptData(allKeys, otp));
        System.out.println(phoneNo);
        System.out.println(myMessage);
        System.out.println(consentDataCache.get(consentDataDTO.getPatientId()));
        return sendOTP(phoneNo, myMessage);
//        return 69;
    }

    @GetMapping("/validate-keys")
    public Integer validateKeys(@RequestParam Integer patientId, @RequestParam String otp) {
        String encrAllKeys = keyCheckCache.getIfPresent(patientId);

        System.out.println(encrAllKeys);
        String decrAllKeys = decryptData(encrAllKeys, Integer.parseInt(otp));
        System.out.println(decrAllKeys);

        Map<Integer, String> hospitalIps = hospitalMapCache.getIfPresent(patientId);
        boolean isValid=true;
        int hospitalIndex=0;
        for (Map.Entry<Integer, String> entry : hospitalIps.entrySet()) {
//            System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
            String temp = decrAllKeys.substring(hospitalIndex*5, (hospitalIndex*5)+5);
            hospitalIndex++;
//            System.out.println(temp);
            String uri = "http://"+entry.getValue()+":"+env.getProperty("app.hospital_port")+"/validate-patient?patientId="+patientId+"&str="+temp;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer "+registeredHospitalRepository.getTokenByHospitalId(entry.getKey()));
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Boolean> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, boolean.class);

//            RestTemplate restTemplate = new RestTemplate();
//            boolean result = restTemplate.getForObject(uri, boolean.class);
            isValid = isValid & result.getBody();
        }
        System.out.println(isValid);
        if(isValid) {
            ConsentDataDTO consentDataDTO = consentDataCache.getIfPresent(patientId);
            Date date = Date.valueOf(LocalDate.now());
            ConsentData consentData = ConsentData.builder()
                    .consentRequestId(consentDataDTO.getConsentRequestId())
                    .patientId(consentDataDTO.getPatientId())
                    .doctorId(consentDataDTO.getDoctorId())
                    .duration(consentDataDTO.getDuration())
                    .consentTimeStamp(date)
                    .activationStatus(consentDataDTO.getActivationStatus())
                    .consentType(consentDataDTO.getConsentType())
                    .build();
            consentDataCache.put(consentDataDTO.getPatientId(), consentDataDTO);

            System.out.println(consentData);
            Integer consentId = consentDataService.saveConsentData(consentData);
            for (int i = 0; i < consentDataDTO.getConsentDataMappingDTOList().size(); i++) {
                ConsentDataMapping consentDataMapping = ConsentDataMapping.builder()
                        .recordId(consentDataDTO.getConsentDataMappingDTOList().get(i).getRecordId())
                        .hospitalId(consentDataDTO.getConsentDataMappingDTOList().get(i).getHospitalId())
                        .consentData(consentData)
                        .build();
                consentDataService.saveConsentDataMapping(consentDataMapping);
            }
            consentRequestService.saveConsentResponce(consentDataDTO.getConsentRequestId(),1);
            return consentId;
//            return 0;
        }
        else
            return -99;
    }

    @GetMapping("/active-consents")
    public List<ConsentData> activeConsent(@RequestParam Integer patientId){
        List<ConsentData> activeConsentList=consentDataService.activeConsent(patientId);
        return activeConsentList;
    }

    @PostMapping("/revoke-consent")
    public Boolean revokeConsent(@RequestParam Integer consentId){
        Integer revokeConsentId= consentDataService.revokeConsent(consentId);
        return revokeConsentId>0;
    }

    @GetMapping("/active-consents-doctor")
    public List<ActiveConsentDTO> activeConsentForDoctor(@RequestParam Integer doctorId, @RequestParam Integer hospitalId){
        List<ActiveConsentDTO> activeConsentList=consentDataService.activeConsentOfDoctor(doctorId,hospitalId);
        return activeConsentList;
    }

    @PostMapping("/grant-emergency-consent")
    public Integer grantEmergencyConsent(@RequestParam Integer pId,@RequestParam Integer dId, @RequestParam Integer hId, @RequestParam Integer authId) {
        ConsentRequest consentRequest = new ConsentRequest();
        consentRequest.setDoctorId(dId);
        consentRequest.setPatientId(pId);
        consentRequest.setHospitalId(hId);
        consentRequest.setPurpose("Emergency Access by Doctor ID: "+dId+" Hospital ID: "+hId+" Authorized by Authorizer ID: "+authId);
        consentRequest.setConsentRequestStatus(APPROVED);
        Integer conReqId = consentRequestService.saveConsentRequest(consentRequest);
        // Created Consent Request for emergency access

        ConsentData consentData = new ConsentData();
        consentData.setConsentType(1);
        consentData.setConsentRequestId(conReqId);
        consentData.setPatientId(pId);
        consentData.setDoctorId(dId);
        consentData.setDuration(1);
        consentData.setActivationStatus(1);
        Date date = Date.valueOf(LocalDate.now());
        consentData.setConsentTimeStamp(date);

        Integer consentId = consentDataService.saveConsentData(consentData);
        return consentId;
    }
}
