package site.sammati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.sammati.entity.ConsentRequest;
import site.sammati.repository.ConsentRequestRepository;
import site.sammati.util.enums.ConsentRequestStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ConsentRequestServiceImpl implements ConsentRequestService{
    @Autowired
    ConsentRequestRepository consentRequestRepository;
    @Override
    public Integer saveConsentRequest(ConsentRequest consentRequest) {
        ConsentRequest cr=consentRequestRepository.save(consentRequest);
        return cr.getConsentRequestId();
    }

    @Override
    public List<Object> getAllConsentList(Integer patientId) {
        return consentRequestRepository.getConsentList(patientId);
    }

    @Override
    public void saveConsentResponce(Integer crid,Integer status) {

        if(status==1)
         consentRequestRepository.updateStatus(crid, ConsentRequestStatus.APPROVED);
        else
            consentRequestRepository.updateStatus(crid, ConsentRequestStatus.REJECTED);
    }
}
