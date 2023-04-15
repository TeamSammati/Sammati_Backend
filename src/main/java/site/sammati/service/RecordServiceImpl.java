package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.ConsentRequestDTO;
import site.sammati.entity.*;
import site.sammati.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    private final PatientHospitalRepository patientHospitalRepository;
    private final RegisteredHospitalRepository registeredHospitalRepository;
    private final ConsentRequestRepository consentRequestRepository;
    private final LogsRepository logsRepository;
    private final ConsentDataRepository consentDataRepository;
    private final ConsentDataMappingRepository consentDataMappingRepository;
    private final Environment env;

    private final DelegationMappingRepository delegationMappingRepository;

    @Override
    public ResponseEntity<Object> handleRecords(Integer patientID, Integer reqType) {
        List<Map<String,Object>> finaldata=new ArrayList<>();
        if(reqType==0){
            List<PatientHospitalMapping> registeredHospitals=patientHospitalRepository.findByPatientId(patientID);

            System.out.println(registeredHospitals);
            for (PatientHospitalMapping i:registeredHospitals) {
                Map<String, Object> allData = new HashMap<String, Object>();
                String ipaddress= registeredHospitalRepository.getIpAddressByHospitalId(i.getHospitalId());
                String hosName= registeredHospitalRepository.gethospitalNameByHospitalId(i.getHospitalId());
                System.out.println("hospitalId "+i.getHospitalId());
                System.out.println("hospitalName "+hosName);
                System.out.println("ipaddress "+ipaddress);
                String uri = "http://"+ipaddress+"/send-records/"+patientID+"/"+reqType;
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", "Bearer "+registeredHospitalRepository.getTokenByHospitalId(i.getHospitalId()));
                System.out.println("saasas"+registeredHospitalRepository.getTokenByHospitalId(i.getHospitalId()));
                HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Object.class);
    //                RestTemplate restTemplate = new RestTemplate();
    //                List<Object> result = restTemplate.getForObject(uri, List.class);
                System.out.println(result);
                allData.put("hospitalId", i.getHospitalId());
                allData.put("hospitalName", registeredHospitalRepository.gethospitalNameByHospitalId(i.getHospitalId()));
                allData.put("data", result.getBody());
                finaldata.add(allData);
            }
            System.out.println(finaldata);
        }
        return new ResponseEntity<Object>(finaldata, HttpStatus.OK);
    }
//    @Override
//    public ResponseEntity<Object> fetchRecordsByConsentRequestId(Integer consentRequestId)
//    {
//        List<Map<String,Object>> finalData=new ArrayList<>();
//        Integer consentId=consentDataRepository.getConsentId(consentRequestId);
//        List<List<Integer>> hospitalRecordMapping=consentDataMappingRepository.findByConsentId(consentId);
//        Map<Integer,ArrayList<Integer>> list=new HashMap<>();
//        for(List<Integer> i: hospitalRecordMapping) {
//            Integer hospitalId = i.get(0);
//            Integer recordId = i.get(1);
//            if (list.get(hospitalId) == null) {
//                list.put(hospitalId, new ArrayList<Integer>());
//            }
//            list.get(hospitalId).add(recordId);
//        }
//
//        for (Map.Entry<Integer,ArrayList<Integer>> entry : list.entrySet())
//        {
//            Map<String, Object> data = new HashMap<String, Object>();
//            String ipaddress= registeredHospitalRepository.getIpAddressByHospitalId(entry.getKey());
//            String hosName= registeredHospitalRepository.gethospitalNameByHospitalId(entry.getKey());
//            ArrayList<Integer> recordIds=entry.getValue();
//            System.out.println("hospitalId "+entry.getKey());
//            System.out.println("hospitalName "+hosName);
//            System.out.println("ipaddress "+ipaddress);
//            System.out.println("recordId "+entry.getValue());
//            String uri = "http://172.16.131.147:6969/api/auth/send_patient_records";
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<ArrayList<Integer>> request = new HttpEntity<ArrayList<Integer>>(recordIds, headers);
//            List<Object> result = restTemplate.postForObject(uri, request , List.class);
//            data.put("hospitalId", entry.getKey());
//            data.put("hospitalName", registeredHospitalRepository.gethospitalNameByHospitalId(entry.getKey()));
//            data.put("data", result);
//            finalData.add(data);
//        }
//
//        addLogs(consentRequestId);
//        return new ResponseEntity<Object>(finalData, HttpStatus.OK);
//    }

    public ResponseEntity<Object> fetchPatientData(Integer consentId)
    {
        List<Map<String,Object>> finalData=new ArrayList<>();
        List<List<Integer>> hospitalRecordMapping=consentDataMappingRepository.findByConsentId(consentId);
        Map<Integer,ArrayList<Integer>> list=new HashMap<>();
        for(List<Integer> i: hospitalRecordMapping) {
            Integer hospitalId = i.get(0);
            Integer recordId = i.get(1);
            if (list.get(hospitalId) == null) {
                list.put(hospitalId, new ArrayList<Integer>());
            }
            list.get(hospitalId).add(recordId);
        }

        for (Map.Entry<Integer,ArrayList<Integer>> entry : list.entrySet())
        {
            Map<String, Object> data = new HashMap<String, Object>();
            String ipaddress= registeredHospitalRepository.getIpAddressByHospitalId(entry.getKey());
            String hosName= registeredHospitalRepository.gethospitalNameByHospitalId(entry.getKey());
            ArrayList<Integer> recordIds=entry.getValue();
            System.out.println("hospitalId "+entry.getKey());
            System.out.println("hospitalName "+hosName);
            System.out.println("ipaddress "+ipaddress);
            System.out.println("recordId "+entry.getValue());
            String uri = "http://"+ipaddress+"/send-patient-records";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer "+registeredHospitalRepository.getTokenByHospitalId(entry.getKey()));
            HttpEntity<ArrayList<Integer>> request = new HttpEntity<ArrayList<Integer>>(recordIds, headers);
            List<Object> result = restTemplate.postForObject(uri, request , List.class);
            data.put("hospitalId", entry.getKey());
            data.put("hospitalName", registeredHospitalRepository.gethospitalNameByHospitalId(entry.getKey()));
            data.put("data", result);
            finalData.add(data);
        }

       // addLogs(consentRequestId);
        return new ResponseEntity<Object>(finalData, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> fetchRecordsByConsentId(Integer cId, Integer dId, Integer hId)
    {

        if(consentDataRepository.findDoctor(cId,dId,hId).size()!=0)
        {
            System.out.println("regular doctor");
            return fetchPatientData(cId);

        }
        else if(delegationMappingRepository.findDoctor(cId,dId,hId).size()!=0)
        {
            System.out.println("delegation doctor");
            return fetchPatientData(cId);
        }
        return null;
    }

    //    @Autowired
    public void addLogs(Integer consentRequestId){

        ConsentRequest consentRequest=consentRequestRepository.findByConsentRequestId(consentRequestId);
        Logs logs= Logs.builder()
                .doctorId(consentRequest.getDoctorId())
                .patientId(consentRequest.getPatientId())
                .consentRequestId(consentRequest.getConsentRequestId())
                .hospitalId(consentRequest.getHospitalId())
                .build();
        logsRepository.save(logs);
    }
}
