package site.sammati.service;

import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;

import java.util.List;

public interface ConsentDataService {
    Integer saveConsentData(ConsentData consentData);

    Integer saveConsentDataMapping(ConsentDataMapping consentDataMapping);

    List<ConsentData> activeConsent(Integer patientId);

    Integer revokeConsent(Integer consentId);
}
