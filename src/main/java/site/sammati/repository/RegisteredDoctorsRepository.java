package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.sammati.entity.RegisteredDoctors;

import java.util.List;

public interface RegisteredDoctorsRepository extends JpaRepository<RegisteredDoctors,Integer> {

    public RegisteredDoctors findByDoctorIdAndAndHospitalId(Integer doctorId,Integer hospitalId);

    @Query("select r.doctorId,r.doctorName from RegisteredDoctors r where r.hospitalId=?1")
    List<Object> getAllDoctorByHospitalId(Integer hospitalId);

    @Query("select r.doctorName from RegisteredDoctors r where r.doctorId=?1 and r.hospitalId=?2")
    String getDoctorName(Integer doctorId,Integer hospitalId);
}
