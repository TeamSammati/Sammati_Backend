package site.sammati.controller;

import jakarta.ws.rs.core.Request;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.service.PatientHospitalMappingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PatientHospitalMappingController
{
    private final PatientHospitalMappingService patientHospitalMappingService;

    @PostMapping("/add_patient_hospital_mapping")
    public Integer add_patient_hospital_mapping(@RequestBody PatientHospitalMapping patientHospitalMapping){
        return patientHospitalMappingService.savePatientHospitalMapping(patientHospitalMapping);
    }
}
