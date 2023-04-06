package site.sammati.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.sammati.dto.ConsentDataMappingDTO;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consentId;

    @Column(nullable = false)
    private Integer consentRequestId;
    @Column(nullable = false)
    private Integer patientId;
    @Column(nullable = false)
    private Integer duration;
    @Column(nullable = false)
    private Integer doctorId;
    private Date consentTimeStamp;

    private Integer consentType;

    @Column(nullable = false)
    private Integer activationStatus;

    @OneToMany(mappedBy = "consentData",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ConsentDataMapping> consentDataMappingList;
}
