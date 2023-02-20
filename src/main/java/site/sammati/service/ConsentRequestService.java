package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.entity.ConsentRequest;

@Component
public interface ConsentRequestService {
    public Integer saveConsentRequest(ConsentRequest consentRequest);
}
