package site.sammati.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "hospital_ip_mapping",uniqueConstraints = { @UniqueConstraint(name = "UniqueIpMapping", columnNames = { "hospitalId", "ipAddress" }) })
public class HospitalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer hospitalId;

    @Column(nullable = false)
    private String hospitalName;

    private String address;

    private Integer registrationNo;
    @Column(nullable = false)
    private String ipAddress;
}
