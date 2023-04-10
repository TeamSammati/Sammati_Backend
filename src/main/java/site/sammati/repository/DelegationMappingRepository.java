package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sammati.entity.DelegationMapping;

import java.util.List;

@Repository
public interface DelegationMappingRepository extends JpaRepository<DelegationMapping,Integer> {

    @Query("select d.consentId,d.doctorId,d.hospitalId from DelegationMapping d where d.consentId=?1 and d.doctorId=?2 and d.hospitalId=?3")
    List<Object> findDoctor(Integer cId, Integer dId, Integer hId);
}
