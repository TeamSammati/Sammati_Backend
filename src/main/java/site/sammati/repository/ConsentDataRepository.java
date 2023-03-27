package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentData;

@Repository
public interface ConsentDataRepository extends JpaRepository<ConsentData,Integer> {

}
