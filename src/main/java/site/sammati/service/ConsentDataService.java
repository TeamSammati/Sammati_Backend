package site.sammati.service;

import site.sammati.dto.ActiveConsentDTO;
import site.sammati.dto.PatientActiveConsentDTO;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;

import java.util.List;

public interface ConsentDataService {
    Integer saveConsentData(ConsentData consentData);

    Integer saveConsentDataMapping(ConsentDataMapping consentDataMapping);

    List<PatientActiveConsentDTO> activeConsent(Integer patientId);

    Integer revokeConsent(Integer consentId);

    List<ActiveConsentDTO> activeConsentOfDoctor(Integer doctorId, Integer hospitalId);

    Integer extendConsent(Integer consentId,Integer days);
}
