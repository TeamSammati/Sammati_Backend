package site.sammati.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import site.sammati.dto.PatientDataDto;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class PatientOtpController
{
    @PostMapping("/generate-otp/{patientId}")
    public Boolean generateOtp(@PathVariable Integer patientId)
    {

        String uri = "http://172.16.131.147:6989/api/auth/generate-otp-patient/"+patientId;
        RestTemplate restTemplate = new RestTemplate();
        boolean result=restTemplate.postForObject(uri,null, Boolean.class);
        return result;
    }
}
