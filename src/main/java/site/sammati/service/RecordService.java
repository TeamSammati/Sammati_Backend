package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.dto.RecordDTO;
import site.sammati.util.enums.ReqType;

import java.util.List;

@Component
public interface RecordService {

    List<RecordDTO> handleRecords(Integer patientID, Integer reqType);
}
