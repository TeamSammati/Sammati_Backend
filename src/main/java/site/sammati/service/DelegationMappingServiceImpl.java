package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.DelegationMapping;
import site.sammati.repository.DelegationMappingRepository;

@Service
@RequiredArgsConstructor
public class DelegationMappingServiceImpl implements DelegationMappingService
{

    private final DelegationMappingRepository delegationMappingRepository;
    @Override
    public Integer saveDelegationMapping(DelegationMapping delegationMapping)
    {
        return delegationMappingRepository.save(delegationMapping).getConsentId();
    }
}
