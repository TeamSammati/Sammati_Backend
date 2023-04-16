package site.sammati.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientActiveConsentDTO {
    private Integer consentId;
    private Integer consentRequestId;
    private Integer doctorId;
    private String doctorName;
    private String hospitalName;
    private Integer hospitalId;
    private Integer consentType;
}
