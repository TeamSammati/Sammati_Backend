package site.sammati.service;

import jakarta.ws.rs.core.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import site.sammati.entity.PatientHospitalMapping;


@Component
public interface PatientHospitalMappingService
{
    Integer savePatientHospitalMapping(PatientHospitalMapping patientHospitalMapping);

    Boolean IsPatientExist(Integer patientId);

    Boolean IsPatientHospitalExist(Integer patientId, Integer hospitalId);

    void changePatientHospitalMapping(Integer patientId, Integer hospitalId);
}
