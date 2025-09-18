package com.restore.restore.dto.response;

import com.restore.restore.domain.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentResponse {
    private Long appointmentId;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;
}
