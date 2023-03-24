package site.sammati.service;

import org.springframework.stereotype.Component;
import site.sammati.entity.HospitalDetails;

@Component
public interface HospitalDetailsService {

    Integer addHospital(HospitalDetails hospitalDetails);

}
