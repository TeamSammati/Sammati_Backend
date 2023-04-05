package site.sammati.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import site.sammati.entity.PatientHospitalMapping;

import java.util.List;

public interface PatientHospitalRepository extends JpaRepository<PatientHospitalMapping,Integer>
{
    @Query("select p.patientId from PatientHospitalMapping p where p.patientId=?1")
    Integer patientExist(Integer patientId);

    public List<PatientHospitalMapping> findByPatientId(Integer patientId);

    @Modifying
    @Transactional
    @Query("UPDATE PatientHospitalMapping set status=1 where patientId=?1 and hospitalId=?2")
    void changeMapping(Integer patientId, Integer hospitalId);
}
