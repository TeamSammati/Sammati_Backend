package site.sammati.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import site.sammati.dto.RecordDTO;
import site.sammati.util.enums.ReqType;

import java.util.List;

@Component
public interface RecordService {

    ResponseEntity<Object> handleRecords(Integer patientID, Integer reqType);

    ResponseEntity<Object> fetchRecordsByConsentRequestId(Integer consentRequestId);
}
