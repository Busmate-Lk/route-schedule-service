package com.busmate.routeschedule.dto.common;

import lombok.Data;

@Data
public class LocationDto {
    private Double latitude;
    private Double longitude;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
