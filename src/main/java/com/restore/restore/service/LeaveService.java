package com.restore.restore.service;

import com.restore.restore.domain.LeaveStatus;
import com.restore.restore.domain.Role;
import com.restore.restore.dto.request.LeaveRequest;
import com.restore.restore.dto.response.LeaveResponse;
import com.restore.restore.entity.Leave;
import com.restore.restore.entity.User;
import com.restore.restore.mapper.LeaveMapper;
import com.restore.restore.repository.LeaveRepository;
import com.restore.restore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;

    // Doctor requests leave
    public LeaveResponse requestLeave(String doctorEmail, LeaveRequest dto) {
        User doctor = userRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!doctor.getRole().equals(Role.DOCTOR)) {
            throw new RuntimeException("Only doctors can request leave");
        }

        // Convert DTO → Entity
        Leave leave = LeaveMapper.toEntity(dto, doctor);
        leave.setStatus(LeaveStatus.PENDING);

        leaveRepository.save(leave);

        // Convert Entity → Response DTO
        return LeaveMapper.toResponse(leave);
    }

    // Doctor views their leave requests
    public List<LeaveResponse> getDoctorLeaves(String doctorEmail) {
        User doctor = userRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return leaveRepository.findByDoctor(doctor).stream()
                .map(LeaveMapper::toResponse)
                .toList();
    }

    // Admin views all leave requests
    public List<LeaveResponse> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(LeaveMapper::toResponse)
                .toList();
    }

    // Admin approves/rejects leave
    public LeaveResponse updateLeaveStatus(Long leaveId, LeaveStatus status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leave.setStatus(status);
        leaveRepository.save(leave);

        return LeaveMapper.toResponse(leave);
    }
}

