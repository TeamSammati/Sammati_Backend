package site.sammati.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientOtpDto
{
    private Integer patientId;
    private Integer hospitalId;
    private String otp;
}
