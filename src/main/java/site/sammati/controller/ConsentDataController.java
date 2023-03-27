package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.dto.ConsentDataDTO;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;
import site.sammati.service.ConsentDataService;

import java.sql.Date;
import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
public class ConsentDataController {

    private final ConsentDataService consentDataService;
    @PostMapping("/receive-consent-data")
    public Integer receiveConsentData(@RequestBody ConsentDataDTO consentDataDTO){
        System.out.println(consentDataDTO);
        Date date = Date.valueOf(LocalDate.now());
        ConsentData consentData=ConsentData.builder()
                .consentRequestId(consentDataDTO.getConsentRequestId())
                .patientId(consentDataDTO.getPatientId())
                .duration(consentDataDTO.getDuration())
                .consentTimeStamp(date)
                .consentType(consentDataDTO.getConsentType())
                .build();
        Integer consentId=consentDataService.saveConsentData(consentData);
        for(int i=0;i<consentDataDTO.getConsentDataMappingDTOList().size();i++){
            ConsentDataMapping consentDataMapping=ConsentDataMapping.builder()
                    .recordId(consentDataDTO.getConsentDataMappingDTOList().get(i).getRecordId())
                    .hospitalId(consentDataDTO.getConsentDataMappingDTOList().get(i).getHospitalId())
                    .consentData(consentData)
                    .build();
            consentDataService.saveConsentDataMapping(consentDataMapping);
        }

        return consentId;
    }
}
