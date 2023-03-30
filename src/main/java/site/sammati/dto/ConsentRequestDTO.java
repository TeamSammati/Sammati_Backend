package site.sammati.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import site.sammati.util.enums.ConsentRequestStatus;

@Data
@Builder
@AllArgsConstructor
public class ConsentRequestDTO {

    private Integer consentRequestId;
    private Integer patientId;
    private Integer doctorId;
    private String doctorName;
    private String hospitalName;
    private Integer hospitalId;
    private ConsentRequestStatus consentRequestStatus;
    private String purpose;
}
