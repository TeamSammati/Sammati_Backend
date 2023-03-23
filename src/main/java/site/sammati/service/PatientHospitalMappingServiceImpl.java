package site.sammati.service;

import jakarta.ws.rs.core.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.controller.PatientHospitalMappingController;
import site.sammati.entity.PatientHospitalMapping;
import site.sammati.repository.PatientHospitalRepository;

@Service
@RequiredArgsConstructor
public class PatientHospitalMappingServiceImpl implements PatientHospitalMappingService{

    private final PatientHospitalRepository patientHospitalRepository;

    @Override
    public Integer savePatientHospitalMapping(PatientHospitalMapping patientHospitalMapping)
    {
        patientHospitalRepository.save(patientHospitalMapping);
        return patientHospitalMapping.getId();
    }
}
