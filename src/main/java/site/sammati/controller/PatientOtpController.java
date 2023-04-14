package site.sammati.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.PatientDataDto;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PatientOtpController
{
    @Autowired
    private final Environment env;
    @GetMapping("/generate-otp/{patientId}")
    public Boolean generateOtp(@PathVariable Integer patientId)
    {

        String uri = "http://"+env.getProperty("app.patient_server")+":"+env.getProperty("app.patient_port")+"/generate-otp-patient/"+patientId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+env.getProperty("app.patient_server_token"));
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Boolean.class);
//        RestTemplate restTemplate = new RestTemplate();
//        boolean result=restTemplate.getForObject(uri, Boolean.class);
        return result.getBody();
    }
}
