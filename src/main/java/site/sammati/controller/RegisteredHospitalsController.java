package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.entity.RegisteredHospitals;
import site.sammati.service.RegisteredHospitalService;

@RestController
@RequiredArgsConstructor
public class RegisteredHospitalsController {

    private final RegisteredHospitalService registeredHospitalService;
    @PostMapping("/add_hospital")
    public Integer addHospital(@RequestBody RegisteredHospitals hospitalDetails){
        return registeredHospitalService.addHospital(hospitalDetails);
    }
}
