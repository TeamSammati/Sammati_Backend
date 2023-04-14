package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.sammati.entity.RegisteredDoctors;
import site.sammati.service.RegisteredDoctorService;
import site.sammati.service.RegisteredHospitalService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class DoctorHospitalMappingController
{
    private final RegisteredDoctorService registeredDoctorService;
    private final RegisteredHospitalService registeredHospitalService;

    @PostMapping("/add-doctor-hospital-mapping")
    public Integer add_doctor_hospital_mapping(@RequestBody RegisteredDoctors registeredDoctors){
        return registeredDoctorService.savePatientHospitalMapping(registeredDoctors);
    }

    @GetMapping("/hospital-with-doctors")
    public ResponseEntity<Object> getHospitalDoctorData()
    {
        System.out.println("in sammati");
        return registeredHospitalService.getHospitalDoctor();
    }
}
