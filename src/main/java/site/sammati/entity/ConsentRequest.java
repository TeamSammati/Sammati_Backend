package site.sammati.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.sammati.util.enums.ConsentRequestStatus;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConsentRequest {
    @Id
    @SequenceGenerator(
            name="consent_request_sequence",
            sequenceName = "consent_request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "consent_request_sequence"
    )
    private Integer consentRequestId;
    private Integer patientId;
    private Integer doctorId;
    private Integer hospitalId;
    private ConsentRequestStatus consentRequestStatus;
}
