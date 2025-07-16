package com.busmate.routeschedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.busmate.routeschedule.dto.common.LocationDto;

@Data
public class StopRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Location is mandatory")
    private LocationDto location;

    private Boolean isAccessible;
}