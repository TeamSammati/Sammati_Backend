package site.sammati.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.sammati.entity.RegisteredDoctors;
import site.sammati.repository.RegisteredDoctorsRepository;

@Service
@RequiredArgsConstructor
public class RegisteredDoctorServiceImpl implements RegisteredDoctorService{

    private final RegisteredDoctorsRepository repository;
    @Override
    public RegisteredDoctors getDoctors(Integer doctorId, Integer hospitalId) {
        return repository.findByDoctorIdAndAndHospitalId(doctorId,hospitalId);
    }

    @Override
    public Integer savePatientHospitalMapping(RegisteredDoctors registeredDoctors) {
        repository.save(registeredDoctors);
        return registeredDoctors.getDoctorId();
    }
}
