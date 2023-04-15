package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.DelegationMapping;

import java.util.List;

@Repository
public interface DelegationMappingRepository extends JpaRepository<DelegationMapping,Integer> {

    @Query("select d.consentId,d.doctorId,d.hospitalId from DelegationMapping d where d.consentId=?1 and d.doctorId=?2 and d.hospitalId=?3")
    List<Object> findDoctor(Integer cId, Integer dId, Integer hId);

    @Query("select c.consentId from DelegationMapping c where c.doctorId=?1 and c.hospitalId=?2")
    List<Integer> delegatedConsentId(Integer doctorId,Integer hospitalId);



    @Query(nativeQuery = true,value = "select c1.consent_id from consent_data c1 natural join consent_request c2 where c1.consent_id=?1 and c2.doctor_id=?2 and c2.hospital_id=?3")
    Integer isConsentDelegable(Integer consentId, Integer requestingDoctorId, Integer requestingHospitalId);
}
