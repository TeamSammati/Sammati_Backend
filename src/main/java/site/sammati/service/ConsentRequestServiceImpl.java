package site.sammati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.sammati.entity.ConsentRequest;
import site.sammati.repository.ConsentRequestRepository;

@Service
public class ConsentRequestServiceImpl implements ConsentRequestService{
    @Autowired
    ConsentRequestRepository consentRequestRepository;
    @Override
    public Integer saveConsentRequest(ConsentRequest consentRequest) {
        ConsentRequest cr=consentRequestRepository.save(consentRequest);
        return cr.getConsentRequestId();
    }
}
