package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sammati.entity.PatientHospitalMapping;

import java.util.List;

public interface PatientHospitalRepository extends JpaRepository<PatientHospitalMapping,Integer>
{
    //use this hansal
    public List<PatientHospitalMapping> findByPatientId(Integer patientId);
}
