package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.DelegationMapping;
import site.sammati.repository.DelegationMappingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DelegationMappingServiceImpl implements DelegationMappingService
{

    private final DelegationMappingRepository delegationMappingRepository;
    @Override
    public Integer saveDelegationMapping(DelegationMapping delegationMapping)
    {
        Integer exist=delegationMappingRepository.isConsentDelegable(delegationMapping.getConsentId(),delegationMapping.getRequestingDoctorId(),delegationMapping.getRequestingHospitalId());
        if(exist!=null)
        {
            return delegationMappingRepository.save(delegationMapping).getId();
        }

        return -99;

    }
}
