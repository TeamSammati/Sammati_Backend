package site.sammati.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;

import java.util.List;

@Repository
public interface ConsentDataRepository extends JpaRepository<ConsentData,Integer>
{
    @Query("select c.consentId from ConsentData c where c.consentRequestId=?1")
    Integer getConsentId(Integer consentRequestId);

    @Query("select c from ConsentData c where c.patientId=?1 and c.activationStatus=1")
    List<ConsentData> getActiveConsent(Integer patientId);

    @Modifying
    @Transactional
    @Query("update ConsentData set activationStatus=0 where consentId=?1")
    Integer updateActivationStatus(Integer consentId);


    @Query(nativeQuery = true,value = "select * from consent_data c natural join consent_request c2 where c.doctor_id=?1 and c2.hospital_id=?2 and c.activation_status=1")
    List<ConsentData> getActiveConsentForDoctor(Integer doctorId,Integer hospitalId);

    @Query("select c.consentId,s.hospitalId,s.doctorId from ConsentData c,ConsentRequest s where c.consentRequestId=s.consentRequestId and c.consentId=?1 and s.doctorId=?2 and s.hospitalId=?3")
    List<Object> findDoctor(Integer consentId, Integer doctorId, Integer hospitalId);

    ConsentData findByConsentId(Integer consentId);
    @Query("select c.patientId from ConsentData c where c.consentId=?1")
    Integer findPatientIdByConsentid(Integer consentId);
    @Query("select c.consentType from ConsentData c where c.consentId=?1")
    Integer findConsentTypeByConsentId(Integer consentId);

    @Modifying
    @Transactional
    @Query("update ConsentData set duration=?2 where consentId=?1")
    Integer extendConsent(Integer consentId, Integer duration);
}
