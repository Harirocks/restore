package com.restore.restore.controller;

import com.restore.restore.domain.DoctorStatus;
import com.restore.restore.dto.request.DoctorRequest;
import com.restore.restore.dto.response.ApiResponse;
import com.restore.restore.entity.Doctor;
import com.restore.restore.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // User applies to become doctor
    @PostMapping("/apply")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Doctor> applyForDoctor(
            @RequestBody DoctorRequest dto,
            Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(doctorService.applyForDoctor(userEmail, dto));
    }

    // Admin approves/rejects doctor
    @PutMapping("/{doctorId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctorStatus(
            @PathVariable Long doctorId,
            @RequestParam DoctorStatus status) {
        return ResponseEntity.ok(doctorService.updateDoctorStatus(doctorId, status));
    }

    // Admin views pending doctor applications
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Doctor>> getPendingDoctors() {
        return ResponseEntity.ok(doctorService.getPendingDoctors());
    }
}
