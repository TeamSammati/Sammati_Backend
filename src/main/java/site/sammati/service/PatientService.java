package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.dto.PatientDataDto;
import site.sammati.dto.PatientOtpDto;

@Component
public interface PatientService
{
    PatientDataDto getPatientData(PatientOtpDto patientOtpDto);

    PatientDataDto getEmergencyPatientData(Integer patientId, Integer hospitalId);

}
