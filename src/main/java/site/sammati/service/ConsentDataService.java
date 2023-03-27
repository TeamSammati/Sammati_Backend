package site.sammati.service;

import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;

public interface ConsentDataService {
    Integer saveConsentData(ConsentData consentData);

    Integer saveConsentDataMapping(ConsentDataMapping consentDataMapping);
}
