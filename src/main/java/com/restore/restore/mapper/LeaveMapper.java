package com.restore.restore.mapper;


import com.restore.restore.domain.LeaveStatus;
import com.restore.restore.dto.request.LeaveRequest;
import com.restore.restore.dto.response.LeaveResponse;
import com.restore.restore.entity.Leave;
import com.restore.restore.entity.User;

public class LeaveMapper {

    // Convert LeaveRequest (incoming) -> Leave Entity
    public static Leave toEntity(LeaveRequest dto, User doctor) {
        return Leave.builder()
                .id(dto.getId())
                .doctor(doctor)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status(dto.getStatus() != null ? dto.getStatus() : LeaveStatus.PENDING)
                .build();
    }

    // Convert Leave Entity -> LeaveRequest (if needed somewhere)
    public static LeaveRequest toRequest(Leave leave) {
        return LeaveRequest.builder()
                .id(leave.getId())
                .doctorName(leave.getDoctor().getUserName())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .build();
    }

    // Convert Leave Entity -> LeaveResponse (for output)
    public static LeaveResponse toResponse(Leave leave) {
        return LeaveResponse.builder()
                .id(leave.getId())
                .doctorName(leave.getDoctor().getUserName())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .build();
    }
}

