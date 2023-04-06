package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.sammati.dto.ConsentRequestDTO;
import site.sammati.entity.ConsentRequest;
import site.sammati.entity.RegisteredDoctors;
import site.sammati.service.ConsentRequestService;
import site.sammati.service.RegisteredDoctorService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class ConsentRequestController {

    @Autowired
    private ConsentRequestService consentRequestService;

    private final RegisteredDoctorService registeredDoctorService;

    @PostMapping("/add-consent-request")
    public Integer generateConsentRequest(@RequestBody ConsentRequest consentRequest){
        System.out.println(consentRequest);
        return consentRequestService.saveConsentRequest(consentRequest);
    }
    @GetMapping("request-list/{id}")
    public List<ConsentRequestDTO> getConsentRequestByPid(@PathVariable("id") Integer patientId){
        List<ConsentRequest> consentRequestList=consentRequestService.getAllConsentList(patientId);
        List<ConsentRequestDTO> consentRequestDTOList=new ArrayList<>();

        for(ConsentRequest consentRequest:consentRequestList){

            RegisteredDoctors registeredDoctors=registeredDoctorService.getDoctors(consentRequest.getDoctorId(),consentRequest.getHospitalId());
            ConsentRequestDTO consentRequestDTO= ConsentRequestDTO.builder()
                    .consentRequestId(consentRequest.getConsentRequestId())
                    .doctorId(consentRequest.getDoctorId())
                    .hospitalId(consentRequest.getHospitalId())
                    .purpose(consentRequest.getPurpose())
                    .consentRequestStatus(consentRequest.getConsentRequestStatus())
                    .doctorName(registeredDoctors.getDoctorName())
                    .hospitalName(registeredDoctors.getHospitalName())
                    .patientId(consentRequest.getPatientId())
                    .build();
            consentRequestDTOList.add(consentRequestDTO);
        }

        return consentRequestDTOList;
    }
    @GetMapping("/get-status/{pid}/{did}/{hid}")
    public List<Object> getConsentRequestStatus(@PathVariable("pid") Integer patientId,@PathVariable("did") Integer doctorId,@PathVariable("hid") Integer hospitalId){
        return consentRequestService.getConsentRequestStatus(patientId,doctorId,hospitalId);
    }
    @GetMapping("/get-status-all/{did}/{hid}")
    public List<Object> getConsentRequestStatusall(@PathVariable("did") Integer doctorId,@PathVariable("hid") Integer hospitalId){
        return consentRequestService.getConsentRequestStatusall(doctorId,hospitalId);
    }

    @PostMapping("/response/{crid}/{status}")
    public Integer generateResponse(@PathVariable("crid") Integer crid,@PathVariable("status") Integer status){
        return consentRequestService.saveConsentResponce(crid, status);
    }

}
