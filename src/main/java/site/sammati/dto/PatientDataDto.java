package site.sammati.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDataDto
{
    private Integer patientId;
    private String firstName;
    private String LastName;
    private String phoneNumber;
    private String gender;
    private String uidNumber;
    private String uidType;
    private String email;
    private String password;
    private Date DOB;
    private Date registrationDate;
    private String state;
    private String userName;
    private String address;
    private String pinCode;
    private String passPhoto;
}
