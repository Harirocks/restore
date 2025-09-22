package com.restore.restore.dto.response;

import com.restore.restore.domain.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class LeaveResponse {
    private Long id;
    private String doctorName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status;
}
