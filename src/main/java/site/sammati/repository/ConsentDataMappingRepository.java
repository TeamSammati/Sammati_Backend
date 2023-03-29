package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;

import java.util.List;

@Repository
public interface ConsentDataMappingRepository extends JpaRepository<ConsentDataMapping,Integer>
{
    @Query(nativeQuery = true,value = "select hospital_id,record_id from consent_data_mapping  where consent_id=?1 ")
    public List<List<Integer>> findByConsentId(Integer consentId);
}
