package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sammati.entity.RegisteredDoctors;

public interface RegisteredDoctorsRepository extends JpaRepository<RegisteredDoctors,Integer> {

    public RegisteredDoctors findByDoctorIdAndAndHospitalId(Integer doctorId,Integer hospitalId);
}
