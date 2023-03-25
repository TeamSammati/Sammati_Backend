package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.sammati.entity.HospitalDetails;
import site.sammati.service.HospitalDetailsService;

@RestController
@RequiredArgsConstructor
public class HospitalDetailsController {

    private final HospitalDetailsService hospitalDetailsService;
    @PostMapping("/add_hospital")
    public Integer addHospital(@RequestBody HospitalDetails hospitalDetails){
        return hospitalDetailsService.addHospital(hospitalDetails);
    }
}
