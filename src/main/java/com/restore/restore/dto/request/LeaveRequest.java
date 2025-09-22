package com.restore.restore.dto.request;

import com.restore.restore.domain.LeaveStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LeaveRequest {
    private Long id;
    private String doctorName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
}

