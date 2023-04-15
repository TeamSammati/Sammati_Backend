package site.sammati.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActiveConsentDTO {
        private Integer consentId;
        private Integer consentRequestId;
        private Integer patientId;
        private Integer consentType;

}
