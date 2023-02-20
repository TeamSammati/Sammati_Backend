package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentRequest;

@Repository
public interface ConsentRequestRepository extends JpaRepository<ConsentRequest, Integer> {

}
