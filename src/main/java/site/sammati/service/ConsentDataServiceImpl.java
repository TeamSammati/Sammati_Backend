package site.sammati.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;
import site.sammati.repository.ConsentDataMappingRepository;
import site.sammati.repository.ConsentDataRepository;

import java.util.List;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    public List<ConsentData> activeConsent(Integer patientId){
        return consentDataRepository.getActiveConsent(patientId);
    }

    public Integer revokeConsent(Integer consentId){
        return consentDataRepository.updateActivationStatus(consentId);
    }

    public List<ConsentData> activeConsentOfDoctor(Integer doctorId){
        return consentDataRepository.getActiveConsentForDoctor(doctorId);
    }
}
