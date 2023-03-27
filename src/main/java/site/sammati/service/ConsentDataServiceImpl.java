package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;
import site.sammati.repository.ConsentDataMappingRepository;
import site.sammati.repository.ConsentDataRepository;

@Service
@RequiredArgsConstructor
public class ConsentDataServiceImpl implements ConsentDataService{

    private final ConsentDataRepository consentDataRepository;
    private final ConsentDataMappingRepository consentDataMappingRepository;
    @Override
    public Integer saveConsentData(ConsentData consentData) {
        return consentDataRepository.save(consentData).getConsentId();
    }

    @Override
    public Integer saveConsentDataMapping(ConsentDataMapping consentDataMapping) {
        return consentDataMappingRepository.save(consentDataMapping).getRhmid();
    }
}
