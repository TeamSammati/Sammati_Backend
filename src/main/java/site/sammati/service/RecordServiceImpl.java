package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.RecordDTO;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.repository.HospitalDetailsRepository;
import site.sammati.repository.PatientHospitalRepository;
import site.sammati.util.enums.ReqType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    private final PatientHospitalRepository patientHospitalRepository;
    private final HospitalDetailsRepository hospitalDetailsRepository;

    @Override
    public ResponseEntity<Object> handleRecords(Integer patientID, Integer reqType) {
        Map<String, Object> allData = new HashMap<String, Object>();
        if(reqType==0){
            List<PatientHospitalMapping> registeredHospitals=patientHospitalRepository.findByPatientId(patientID);
            System.out.println(registeredHospitals);
            for (PatientHospitalMapping i:registeredHospitals) {
                String ipaddress=hospitalDetailsRepository.getIpAddressByHospitalId(i.getHospitalId());
                String hosName=hospitalDetailsRepository.gethospitalNameByHospitalId(i.getHospitalId());
                System.out.println("hospitalName "+hosName);
                String uri = "http://"+ipaddress+":6969/send_records/"+patientID+"/"+reqType;
                RestTemplate restTemplate = new RestTemplate();
                List<Object> result = restTemplate.getForObject(uri, List.class);
                System.out.println(result);

                allData.put(hosName, result);

            }
        }
        return new ResponseEntity<Object>(allData, HttpStatus.OK);
    }
}
