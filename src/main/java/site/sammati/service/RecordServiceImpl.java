package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.RecordDTO;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.repository.HospitalDetailsRepository;
import site.sammati.repository.PatientHospitalRepository;
import site.sammati.util.enums.ReqType;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    private final PatientHospitalRepository patientHospitalRepository;
    private final HospitalDetailsRepository hospitalDetailsRepository;

    @Override
    public List<RecordDTO> handleRecords(Integer patientID, Integer reqType) {
        if(reqType==0){
            List<PatientHospitalMapping> registeredHospitals=patientHospitalRepository.findByPatientId(patientID);
            System.out.println(registeredHospitals);
            for (PatientHospitalMapping i:registeredHospitals) {
                String ipaddress=hospitalDetailsRepository.getIpAddressByHospitalId(i.getHospitalId());
                System.out.println("hospitalid "+i.getHospitalId());
                System.out.println(ipaddress+" sasasasasas");
                String uri = "http://"+ipaddress+":6969/send_records/"+patientID+"/"+reqType;
                RestTemplate restTemplate = new RestTemplate();
                List<Object> result = restTemplate.getForObject(uri, List.class);
                System.out.println(result);

            }
        }
        return null;
    }
}
