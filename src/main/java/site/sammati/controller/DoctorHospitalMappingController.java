package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.entity.RegisteredDoctors;
import site.sammati.service.RegisteredDoctorService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class DoctorHospitalMappingController
{
    private final RegisteredDoctorService registeredDoctorService;

    @PostMapping("/add-doctor-hospital-mapping")
    public Integer add_doctor_hospital_mapping(@RequestBody RegisteredDoctors registeredDoctors){
        return registeredDoctorService.savePatientHospitalMapping(registeredDoctors);
    }
}
