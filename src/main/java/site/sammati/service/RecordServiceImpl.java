package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.repository.RegisteredHospitalRepository;
import site.sammati.repository.PatientHospitalRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    private final PatientHospitalRepository patientHospitalRepository;
    private final RegisteredHospitalRepository registeredHospitalRepository;

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
                String uri = "http://"+ipaddress+":6969/api/auth/send_records/"+patientID+"/"+reqType;
                RestTemplate restTemplate = new RestTemplate();
                List<Object> result = restTemplate.getForObject(uri, List.class);
                System.out.println(result);
                allData.put("hospitalId", i.getHospitalId());
                allData.put("hospitalName", registeredHospitalRepository.gethospitalNameByHospitalId(i.getHospitalId()));
                allData.put("data", result);
                finaldata.add(allData);
            }
            System.out.println(finaldata);
        }
        return new ResponseEntity<Object>(finaldata, HttpStatus.OK);
    }
}
