package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.HospitalDetails;
import site.sammati.repository.HospitalDetailsRepository;

@Service
@RequiredArgsConstructor
public class HospitalDetailsServiceImpl implements HospitalDetailsService{
    private final HospitalDetailsRepository hospitalDetailsRepository;
    @Override
    public Integer addHospital(HospitalDetails hospitalDetails) {
        return hospitalDetailsRepository.save(hospitalDetails).getHospitalId();
    }
}
