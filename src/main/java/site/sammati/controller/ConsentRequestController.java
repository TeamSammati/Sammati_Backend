package site.sammati.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.sammati.entity.ConsentRequest;
import site.sammati.service.ConsentRequestService;

import java.util.List;

@RestController
public class ConsentRequestController {

    @Autowired
    private ConsentRequestService consentRequestService;

    @PostMapping("/consent_request")
    public Integer generateConsentRequest(@RequestBody ConsentRequest consentRequest){
        return consentRequestService.saveConsentRequest(consentRequest);
    }
    @GetMapping("Request_List/{id}")
    public List<Object> getConsentRequestByPid(@PathVariable("id") Integer patientId){
        return consentRequestService.getAllConsentList(patientId);
    }
}