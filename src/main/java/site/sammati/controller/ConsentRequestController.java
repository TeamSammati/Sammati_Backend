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
    @GetMapping("/get_status/{pid}/{did}/{hid}")
    public List<Object> getConsentRequestStatus(@PathVariable("pid") Integer patientId,@PathVariable("did") Integer doctorId,@PathVariable("hid") Integer hospitalId){
        return consentRequestService.getConsentRequestStatus(patientId,doctorId,hospitalId);
    }

    @PostMapping("/response/{crid}/{status}")
    public void generateResponse(@PathVariable("crid") Integer crid,@PathVariable("status") Integer status){
        consentRequestService.saveConsentResponce(crid, status);
    }

}
