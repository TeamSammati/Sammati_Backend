package site.sammati.controller;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.sammati.service.RecordService;
import site.sammati.util.enums.ReqType;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/handle-records")
    public ResponseEntity<Object> handleRecords(@RequestParam Integer patientID, @RequestParam Integer reqType){
        return recordService.handleRecords(patientID,reqType);
    }

    @PostMapping("/fetch-records-by-consent-id/{consentId}/{doctorId}/{hospitalId}")
    public ResponseEntity<Object> fetchRecordsByConsentId(@PathVariable("consentId") Integer consentId,@PathVariable("doctorId") Integer doctorId, @PathVariable("hospitalId") Integer hospitalId)
    {
        System.out.println("in sammati");
        return recordService.fetchRecordsByConsentId(consentId,doctorId,hospitalId);
    }
}
