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
    @Query("select c from ConsentRequest c where c.patientId=?1")
    public List<Object> getConsentList(Integer patientId);

    @Modifying
    @Transactional
    @Query("update ConsentRequest set consentRequestStatus=?2 where consentRequestId=?1")
    void updateStatus(Integer crid, ConsentRequestStatus status);
}
