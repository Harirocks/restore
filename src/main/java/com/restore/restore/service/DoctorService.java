package com.restore.restore.service;

import com.restore.restore.domain.DoctorStatus;
import com.restore.restore.domain.Role;
import com.restore.restore.dto.request.DoctorRequest;
import com.restore.restore.entity.Doctor;
import com.restore.restore.entity.User;
import com.restore.restore.repository.DoctorRepository;
import com.restore.restore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    // User applies to become a Doctor
    public Doctor applyForDoctor(String userEmail, DoctorRequest dto) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Role.USER)) {
            throw new RuntimeException("Only regular users can apply as doctors");
        }

        Doctor doctor = Doctor.builder()
                .user(user)
                .specialization(dto.getSpecialization())
                .qualification(dto.getQualification())
                .hospitalName(dto.getHospitalName())
                .status(DoctorStatus.PENDING)
                .build();

        return doctorRepository.save(doctor);
    }

    // Admin approves/rejects doctor
    public Doctor updateDoctorStatus(Long doctorId, DoctorStatus status) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor application not found"));

        doctor.setStatus(status);

        if (status == DoctorStatus.APPROVED) {
            User user = doctor.getUser();
            user.setRole(Role.DOCTOR);
            userRepository.save(user);
        }

        return doctorRepository.save(doctor);
    }

    // Admin views pending doctor applications
    public List<Doctor> getPendingDoctors() {
        return doctorRepository.findByStatus(DoctorStatus.PENDING);
    }
}

