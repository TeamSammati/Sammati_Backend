package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.entity.RegisteredHospitals;

@Component
public interface RegisteredHospitalService {

    Integer addHospital(RegisteredHospitals hospitalDetails);

}
