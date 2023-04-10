package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.entity.DelegationMapping;

@Component
public interface DelegationMappingService 
{

    void saveDelegationMapping(DelegationMapping delegationMapping);
}
