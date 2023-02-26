package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.entity.ConsentRequest;

import java.util.List;

@Component
public interface ConsentRequestService {
    public Integer saveConsentRequest(ConsentRequest consentRequest);

    List<Object> getAllConsentList(Integer patientId);

    public List<Object> getConsentRequestStatus(Integer patientId, Integer doctorId, Integer hospitalId);
    public List<Object> getConsentRequestStatusall(Integer doctorId, Integer hospitalId);

    public Integer saveConsentResponce(Integer crid,Integer status);
}
