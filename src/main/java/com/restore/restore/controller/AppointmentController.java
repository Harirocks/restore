package com.restore.restore.controller;

import com.restore.restore.dto.request.AppointmentRequest;
import com.restore.restore.dto.response.AppointmentResponse;
import com.restore.restore.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @RequestBody AppointmentRequest request,
            Authentication authentication) {

        String userEmail = authentication.getName();
        return ResponseEntity.ok(appointmentService.bookAppointment(userEmail, request));
    }

    @GetMapping("/my-appointments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AppointmentResponse>> getUserAppointments(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(appointmentService.getUserAppointments(userEmail));
    }

    @GetMapping("/doctor-appointments")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<AppointmentResponse>> getDoctorAppointments(Authentication authentication) {
        String doctorEmail = authentication.getName();
        return ResponseEntity.ok(appointmentService.getDoctorAppointments(doctorEmail));
    }
}

