package site.sammati.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.RegisteredHospitals;

@Repository
public interface RegisteredHospitalRepository extends JpaRepository<RegisteredHospitals,Integer> {

    @Query("select ipAddress from RegisteredHospitals where hospitalId=?1")
    String getIpAddressByHospitalId(Integer hospitalId);

    @Query("select hospitalName from RegisteredHospitals where hospitalId=?1")
    String gethospitalNameByHospitalId(Integer hospitalId);
}
