package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.entity.DelegationMapping;

@Component
public interface DelegationMappingService 
{

    Integer saveDelegationMapping(DelegationMapping delegationMapping);
}
