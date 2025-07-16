package com.busmate.routeschedule.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PassengerServicePermitScheduleAssignmentRequest {
    @NotNull(message = "Permit ID is mandatory")
    private UUID permitId;

    @NotNull(message = "Schedule ID is mandatory")
    private UUID scheduleId;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    private LocalDate endDate;

    private String status = "active";
}
