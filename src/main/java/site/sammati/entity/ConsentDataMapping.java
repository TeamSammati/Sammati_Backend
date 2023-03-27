package site.sammati.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = { @UniqueConstraint(name = "ConsentDataMapping", columnNames = { "hospitalId", "recordId" }) })
public class ConsentDataMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rhmid;

    private Integer hospitalId;

    private Integer recordId;
    @ManyToOne
    @JoinColumn(name = "consentId",nullable = false)
    private ConsentData consentData;
}
