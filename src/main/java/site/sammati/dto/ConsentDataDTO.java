package site.sammati.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsentDataDTO {
    private Integer consentRequestId;
    private Integer patientId;
    private Integer duration;
    private Integer consentType;
    private List<ConsentDataMappingDTO> consentDataMappingDTOList;
}
