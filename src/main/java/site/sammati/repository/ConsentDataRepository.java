package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
