package site.sammati.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.entity.ConsentRequest;
import site.sammati.service.ConsentRequestService;

@RestController
public class ConsentRequestController {

    @Autowired
    private ConsentRequestService consentRequestService;

    @PostMapping("/consent_request")
    public Integer generateConsentRequest(@RequestBody ConsentRequest consentRequest){
        return consentRequestService.saveConsentRequest(consentRequest);
    }
}
