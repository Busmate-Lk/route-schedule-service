package com.busmate.routeschedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OperatorRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String operatorType = "PRIVATE";

    private String region;

    private String status = "active";
}
