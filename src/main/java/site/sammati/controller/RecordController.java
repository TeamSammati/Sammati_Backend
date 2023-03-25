package site.sammati.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.service.RecordService;
import site.sammati.util.enums.ReqType;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/handle_records")
    public ResponseEntity<Object> handleRecords(@RequestParam Integer patientID, @RequestParam Integer reqType){
        return recordService.handleRecords(patientID,reqType);
    }
}
