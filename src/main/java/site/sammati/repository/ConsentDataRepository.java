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

    @Query("select c from ConsentData c where c.doctorId=?1 and c.activationStatus=1")
    List<ConsentData> getActiveConsentForDoctor(Integer doctorId);
}
