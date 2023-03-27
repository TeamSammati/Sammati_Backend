package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.RegisteredHospitals;
import site.sammati.repository.RegisteredHospitalRepository;

@Service
@RequiredArgsConstructor
public class RegisteredHospitalServiceImpl implements RegisteredHospitalService {
    private final RegisteredHospitalRepository registeredHospitalRepository;
    @Override
    public Integer addHospital(RegisteredHospitals hospitalDetails) {
        return registeredHospitalRepository.save(hospitalDetails).getHospitalId();
    }
}
