package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import site.sammati.entity.RegisteredDoctors;
import site.sammati.entity.RegisteredHospitals;
import site.sammati.repository.RegisteredDoctorsRepository;
import site.sammati.repository.RegisteredHospitalRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegisteredHospitalServiceImpl implements RegisteredHospitalService {
    private final RegisteredHospitalRepository registeredHospitalRepository;
    private final RegisteredDoctorsRepository registeredDoctorsRepository;
    @Override
    public Integer addHospital(RegisteredHospitals hospitalDetails) {
        return registeredHospitalRepository.save(hospitalDetails).getHospitalId();
    }

    @Override
    public ResponseEntity<Object> getHospitalDoctor()
    {
        List<RegisteredHospitals> hospitalMapping=registeredHospitalRepository.getHospitalDetails();
        List<Map<String,Object>> finalData=new ArrayList<>();
        for(RegisteredHospitals i:hospitalMapping)
        {
            Map<String,Object>data=new HashMap<>();
            data.put("hospitalId",i.getHospitalId());
            data.put("hospitalName",i.getHospitalName());
            List<Object>doctor= registeredDoctorsRepository.getAllDoctorByHospitalId(i.getHospitalId());
            System.out.println(doctor);
            data.put("doctors",doctor);
            finalData.add(data);
        }
        return new ResponseEntity<Object>(finalData, HttpStatus.OK);
    }
}
