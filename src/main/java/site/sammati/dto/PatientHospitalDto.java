package site.sammati.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientHospitalDto
{
    private Integer patientId;
    private Integer hospitalId;

}
