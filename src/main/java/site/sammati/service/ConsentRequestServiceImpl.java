package site.sammati.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.sammati.entity.ConsentRequest;
import site.sammati.repository.ConsentRequestRepository;
import site.sammati.util.enums.ConsentRequestStatus;
import java.util.List;

@Service
public class ConsentRequestServiceImpl implements ConsentRequestService{
    @Autowired
    ConsentRequestRepository consentRequestRepository;
    @Override
    public Integer saveConsentRequest(ConsentRequest consentRequest) {
        consentRequest.setConsentRequestStatus(ConsentRequestStatus.PENDING);
        ConsentRequest cr=consentRequestRepository.save(consentRequest);
        return cr.getConsentRequestId();
    }

    @Override
    public List<Object> getAllConsentList(Integer patientId) {
        return consentRequestRepository.getConsentList(patientId);
    }

    @Override
    public Integer saveConsentResponce(Integer crid,Integer status) {

        if(status==1)
            return consentRequestRepository.updateStatus(crid, ConsentRequestStatus.APPROVED);
        else if(status==2)
            return consentRequestRepository.updateStatus(crid, ConsentRequestStatus.REJECTED);
        else
            return -1;
    }

    @Override
    public List<Object> getConsentRequestStatus(Integer patientId, Integer doctorId, Integer hospitalId) {
        return consentRequestRepository.getConsentRequestStatus(patientId,doctorId,hospitalId);
    }

    @Override
    public List<Object> getConsentRequestStatusall(Integer doctorId, Integer hospitalId) {
        return consentRequestRepository.getConsentRequestStatusall(doctorId,hospitalId);    }
}
