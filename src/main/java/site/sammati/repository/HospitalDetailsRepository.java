package site.sammati.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.HospitalDetails;

@Repository
public interface HospitalDetailsRepository extends JpaRepository<HospitalDetails,Integer> {

    @Query("select ipAddress from HospitalDetails where hospitalId=?1")
    String getIpAddressByHospitalId(Integer hospitalId);
}
