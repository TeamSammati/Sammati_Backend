package site.sammati.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.dto.ConsentDataDTO;

@RestController
public class ConsentDataController {

    @PostMapping("/receive-consent-data")
    public Integer receiveConsentData(@RequestBody ConsentDataDTO consentDataDTO){
        System.out.println(consentDataDTO);
        return 0;
    }
}
