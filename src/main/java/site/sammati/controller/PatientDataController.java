package site.sammati.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.sammati.dto.PatientDataDto;
import site.sammati.dto.PatientOtpDto;
import site.sammati.entity.RegisteredDoctors;
import site.sammati.service.PatientService;
import site.sammati.service.PatientServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PatientDataController
{
    private final PatientService patientService;

    @PostMapping("/get-patient-data-by-patient-id")
    public PatientDataDto get_patient_data(@RequestBody PatientOtpDto patientOtpDto)
    {
        System.out.println("in sammati");
        return patientService.getPatientData(patientOtpDto);
    }

    @PostMapping("/get-emergency-patient-data-by-patient-id")
    public PatientDataDto get_emergency_patient_data(@RequestParam Integer patientId, @RequestParam Integer hospitalId)
    {
        return patientService.getEmergencyPatientData(patientId, hospitalId);
    }
}
