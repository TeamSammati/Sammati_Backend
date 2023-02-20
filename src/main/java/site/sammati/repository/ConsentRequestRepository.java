package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentRequest;

import java.util.List;
import java.util.Objects;

@Repository
public interface ConsentRequestRepository extends JpaRepository<ConsentRequest, Integer>
{
    @Query("select c from ConsentRequest c where c.patientId=?1")
    public List<Object> getConsentList(Integer patientId);
}
