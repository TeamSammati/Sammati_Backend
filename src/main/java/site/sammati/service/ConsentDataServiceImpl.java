package site.sammati.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.dto.ActiveConsentDTO;
import site.sammati.entity.ConsentData;
import site.sammati.entity.ConsentDataMapping;
import site.sammati.repository.ConsentDataMappingRepository;
import site.sammati.repository.ConsentDataRepository;
import site.sammati.repository.DelegationMappingRepository;

import java.text.ParseException;
import java.util.*;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ConsentDataServiceImpl implements ConsentDataService{

    private final ConsentDataRepository consentDataRepository;
    private final ConsentDataMappingRepository consentDataMappingRepository;
    private final DelegationMappingRepository delegationMappingRepository;

    @Override
    public Integer saveConsentData(ConsentData consentData) {
        return consentDataRepository.save(consentData).getConsentId();
    }

    @Override
    public Integer saveConsentDataMapping(ConsentDataMapping consentDataMapping) {
        return consentDataMappingRepository.save(consentDataMapping).getRhmid();
    }

    public List<ConsentData> activeConsent(Integer patientId){
        return consentDataRepository.getActiveConsent(patientId);
    }

    public Integer revokeConsent(Integer consentId){
        return consentDataRepository.updateActivationStatus(consentId);
    }

    public List<ActiveConsentDTO> activeConsentOfDoctor(Integer doctorId, Integer hospitalId){
        List<ConsentData> consentData=consentDataRepository.getActiveConsentForDoctor(doctorId,hospitalId);
        List<Integer> delegatedConsentId=delegationMappingRepository.delegatedConsentId(doctorId,hospitalId);

        for(Integer c: delegatedConsentId){
            ConsentData data=consentDataRepository.findByConsentId(c);
            if(data!=null)consentData.add(data);
        }

        for(ConsentData c:consentData){
            Calendar cal=Calendar.getInstance();
            try{
                cal.setTime(c.getConsentTimeStamp());
            }catch(Exception e){
                e.printStackTrace();
            }
            cal.add(Calendar.DAY_OF_MONTH, c.getDuration());
            if(cal.getTime().before(new Date())){
                consentDataRepository.updateActivationStatus(c.getConsentId());
                consentData.remove(c);
            }
        }

        List<ActiveConsentDTO> activeConsentDTOS=new ArrayList<>();
        for(ConsentData c:consentData){
            ActiveConsentDTO activeConsentDTO=ActiveConsentDTO.builder()
                    .consentRequestId(c.getConsentRequestId())
                    .consentId(c.getConsentId())
                    .consentType(c.getConsentType())
                    .patientId(c.getPatientId())
                    .build();
            activeConsentDTOS.add(activeConsentDTO);
        }
        return activeConsentDTOS;
    }
}
