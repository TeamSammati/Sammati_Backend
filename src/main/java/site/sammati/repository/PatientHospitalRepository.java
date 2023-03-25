package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.sammati.entity.PatientHospitalMapping;

public interface PatientHospitalRepository extends JpaRepository<PatientHospitalMapping,Integer>
{
    @Query("select p.patientId from PatientHospitalMapping p where p.patientId=?1")
    Integer patientExist(Integer patientId);
}
