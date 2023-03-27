package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentDataMapping;

@Repository
public interface ConsentDataMappingRepository extends JpaRepository<ConsentDataMapping,Integer> {

}
