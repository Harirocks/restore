package com.restore.restore.service;

import com.restore.restore.domain.AppointmentStatus;
import com.restore.restore.domain.Role;
import com.restore.restore.dto.request.AppointmentRequest;
import com.restore.restore.dto.response.AppointmentResponse;
import com.restore.restore.entity.Appointment;
import com.restore.restore.entity.User;
import com.restore.restore.repository.AppointmentRepository;
import com.restore.restore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    // User books appointment
    public AppointmentResponse bookAppointment(String userEmail, AppointmentRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!doctor.getRole().equals(Role.DOCTOR)) {
            throw new RuntimeException("Selected user is not a doctor");
        }

        Appointment appointment = Appointment.builder()
                .user(user)
                .doctor(doctor)
                .appointmentTime(request.getAppointmentTime())
                .status(AppointmentStatus.BOOKED)
                .build();

        appointmentRepository.save(appointment);

        return new AppointmentResponse(
                appointment.getId(),
                doctor.getUserName(),
                appointment.getAppointmentTime(),
                appointment.getStatus()
        );
    }

    // Doctor views their appointments
    public List<AppointmentResponse> getDoctorAppointments(String doctorEmail) {
        User doctor = userRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);

        return appointments.stream()
                .map(a -> new AppointmentResponse(
                        a.getId(),
                        a.getDoctor().getUserName(),
                        a.getAppointmentTime(),
                        a.getStatus()))
                .toList();
    }

    // User views their appointments
    public List<AppointmentResponse> getUserAppointments(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Appointment> appointments = appointmentRepository.findByUser(user);

        return appointments.stream()
                .map(a -> new AppointmentResponse(
                        a.getId(),
                        a.getDoctor().getUserName(),
                        a.getAppointmentTime(),
                        a.getStatus()))
                .toList();
    }
}

