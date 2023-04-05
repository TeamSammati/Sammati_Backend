package site.sammati.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Request;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.service.PatientHospitalMappingService;

import javax.sound.midi.SysexMessage;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PatientHospitalMappingController
{
    private final PatientHospitalMappingService patientHospitalMappingService;

    @PostMapping("/change-patient-hospital-mapping/{pId}/{hId}")
    public void change_patient_hospital_mapping(@PathVariable("pId") Integer patientId ,@PathVariable("hId") Integer hospitalId){
        System.out.println("in sammati");
        patientHospitalMappingService.changePatientHospitalMapping(patientId,hospitalId);
    }

    @GetMapping("/patient_existIn_hospital/{pId}")
    public Boolean check_patient_exist_in_hospital(@PathVariable("pId") Integer patientId)
    {
        return patientHospitalMappingService.IsPatientExist(patientId);
    }

    @PostMapping("/global_patient_id_exist/{pId}")
    public Boolean checkGlobalPatientId(@PathVariable("pId") Integer patientId)
    {
        String uri = "http://172.16.131.147:6989/api/auth/global_patient_id_exist/"+patientId;
        RestTemplate restTemplate = new RestTemplate();
        Boolean response = restTemplate.postForObject(uri, null,  Boolean.class);
        return response;
    }

}
