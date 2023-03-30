package site.sammati.service;

import site.sammati.entity.RegisteredDoctors;

public interface RegisteredDoctorService {
    RegisteredDoctors getDoctors(Integer doctorId, Integer hospitalId);
}
