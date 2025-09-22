package com.restore.restore.controller;

import com.restore.restore.domain.LeaveStatus;
import com.restore.restore.dto.request.LeaveRequest;
import com.restore.restore.dto.response.LeaveResponse;
import com.restore.restore.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    // Doctor requests leave
    @PostMapping("/request")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<LeaveResponse> requestLeave(
            @RequestBody LeaveRequest dto,
            Authentication authentication) {
        String doctorEmail = authentication.getName();
        return ResponseEntity.ok(leaveService.requestLeave(doctorEmail, dto));
    }

    // Doctor views their leaves
    @GetMapping("/my-leaves")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<LeaveResponse>> getDoctorLeaves(Authentication authentication) {
        String doctorEmail = authentication.getName();
        return ResponseEntity.ok(leaveService.getDoctorLeaves(doctorEmail));
    }

    // Admin views all leave requests
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LeaveResponse>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    // Admin approves/rejects leave
    @PutMapping("/{leaveId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LeaveResponse> updateLeaveStatus(
            @PathVariable Long leaveId,
            @RequestParam LeaveStatus status) {
        return ResponseEntity.ok(leaveService.updateLeaveStatus(leaveId, status));
    }
}

