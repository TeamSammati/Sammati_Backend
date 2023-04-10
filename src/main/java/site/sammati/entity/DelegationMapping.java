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
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueMapping", columnNames = { "consentId", "doctorId","hospitalId" }) })
public class DelegationMapping
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private Integer consentId;

    @Column(nullable = false)
    private Integer doctorId;

    @Column(nullable = false)
    private String doctorName;

    @Column(nullable = false)
    private Integer hospitalId;

    @Column(nullable = false)
    private Integer duration;
}
