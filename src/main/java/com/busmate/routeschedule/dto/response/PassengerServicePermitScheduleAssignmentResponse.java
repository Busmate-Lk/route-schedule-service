package com.busmate.routeschedule.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PassengerServicePermitScheduleAssignmentResponse {
    private UUID id;
    private UUID permitId;
    private String permitNumber;
    private UUID scheduleId;
    private String scheduleName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
