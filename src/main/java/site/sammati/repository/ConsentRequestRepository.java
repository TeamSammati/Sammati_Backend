package site.sammati.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentRequest;
import site.sammati.util.enums.ConsentRequestStatus;

import java.util.List;
import java.util.Objects;

@Repository
public interface ConsentRequestRepository extends JpaRepository<ConsentRequest, Integer>
{
    @Query("select c from ConsentRequest c where c.patientId=?1 and c.consentRequestStatus=0")
    public List<Object> getConsentList(Integer patientId);

    @Query("select c.consentRequestId,c.consentRequestStatus from ConsentRequest c where c.patientId=?1 and c.doctorId=?2 and c.hospitalId=?3")
    List<Object> getConsentRequestStatus(Integer patientId, Integer doctorId, Integer hospitalId);

    @Modifying
    @Transactional
    @Query("update ConsentRequest set consentRequestStatus=?2 where consentRequestId=?1")
    Integer updateStatus(Integer crid, ConsentRequestStatus status);

    @Query("select c.patientId,c.consentRequestId,c.consentRequestStatus from ConsentRequest c where c.doctorId=?1 and c.hospitalId=?2")
    List<Object> getConsentRequestStatusall(Integer doctorId, Integer hospitalId);
}
