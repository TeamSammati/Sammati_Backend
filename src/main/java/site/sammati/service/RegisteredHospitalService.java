package site.sammati.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import site.sammati.entity.RegisteredHospitals;

@Component
public interface RegisteredHospitalService {

    Integer addHospital(RegisteredHospitals hospitalDetails);

    ResponseEntity<Object> getHospitalDoctor();
}
