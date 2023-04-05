package site.sammati.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logsId;
    @Column(nullable = false)
    private Integer patientId;

    @Column(nullable = false)
    private Integer consentRequestId;
    @Column(nullable = false)
    private Integer doctorId;

    @Column(nullable = false)
    private Integer hospitalId;
}
