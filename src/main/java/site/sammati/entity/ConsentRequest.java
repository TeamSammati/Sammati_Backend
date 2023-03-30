package site.sammati.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.sammati.util.enums.ConsentRequestStatus;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @Column(nullable = false)
    private Integer consentRequestId;
    @Column(nullable = false)
    private Integer patientId;
    @Column(nullable = false)
    private Integer doctorId;
    @Column(nullable = false)
    private Integer hospitalId;
    @Column(nullable = false)
    private ConsentRequestStatus consentRequestStatus;
    @Column
    private String purpose;
}
