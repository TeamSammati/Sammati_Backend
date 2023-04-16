package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.PatientDataDto;
import site.sammati.dto.PatientOtpDto;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.repository.PatientHospitalRepository;
import site.sammati.repository.RegisteredHospitalRepository;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientHospitalMappingService patientHospitalMappingService;
    private final Environment env;
    @Override
    public PatientDataDto getPatientData(PatientOtpDto patientOtpDto)
    {

        String uri = "http://"+env.getProperty("app.patient_server")+":"+env.getProperty("app.patient_port")+"/send-patient-data";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+env.getProperty("app.patient_server_token"));
        HttpEntity<PatientOtpDto> request = new HttpEntity<PatientOtpDto>(patientOtpDto, headers);
        System.out.println("request in sammati:"+request);
        PatientDataDto result = restTemplate.postForObject(uri,request,PatientDataDto.class);
        System.out.println("datatatatatatatat:"+result);
        if(result==null)
        {
            return null;
        }
        PatientHospitalMapping patientHospitalMapping=new PatientHospitalMapping();
        patientHospitalMapping.setPatientId(patientOtpDto.getPatientId());
        patientHospitalMapping.setHospitalId(patientOtpDto.getHospitalId());
        patientHospitalMapping.setStatus(0);
        Integer sequence=patientHospitalMappingService.savePatientHospitalMapping(patientHospitalMapping);
        System.out.println("id"+sequence);
        return result;
    }

    @Override
    public PatientDataDto getEmergencyPatientData(Integer patientId, Integer hospitalId) {
        String uri = "http://"+env.getProperty("app.patient_server")+":"+env.getProperty("app.patient_port")+"/send-emergency-patient-data?patientId="+patientId;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+env.getProperty("app.patient_server_token"));
        HttpEntity<Void> request = new HttpEntity<>(headers);
        PatientDataDto result = restTemplate.postForObject(uri,request,PatientDataDto.class);
        System.out.println("datatatatatatatat:"+result);
        if(result==null)
        {
            return null;
        }
        Boolean patient_hosptial_exists = patientHospitalMappingService.IsPatientHospitalExist(patientId, hospitalId);
        if(!patient_hosptial_exists)
        {
            PatientHospitalMapping patientHospitalMapping=new PatientHospitalMapping();
            patientHospitalMapping.setPatientId(patientId);
            patientHospitalMapping.setHospitalId(hospitalId);
            patientHospitalMapping.setStatus(0);
            Integer sequence=patientHospitalMappingService.savePatientHospitalMapping(patientHospitalMapping);
        }
        return result;
    }
}
