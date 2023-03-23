package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sammati.entity.PatientHospitalMapping;

public interface PatientHospitalRepository extends JpaRepository<PatientHospitalMapping,Integer>
{
    //use this hansal
}
