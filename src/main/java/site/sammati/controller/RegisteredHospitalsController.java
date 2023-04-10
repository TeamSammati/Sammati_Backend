package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.sammati.entity.RegisteredHospitals;
import site.sammati.service.RegisteredHospitalService;

@RestController
@RequiredArgsConstructor
public class RegisteredHospitalsController {

    private final RegisteredHospitalService registeredHospitalService;
    @PostMapping("/add-hospital")
    public Integer addHospital(@RequestBody RegisteredHospitals hospitalDetails){
        return registeredHospitalService.addHospital(hospitalDetails);
    }


}
