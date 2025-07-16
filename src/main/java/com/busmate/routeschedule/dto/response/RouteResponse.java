package com.busmate.routeschedule.dto.response;

import lombok.Data;
import com.busmate.routeschedule.dto.common.LocationDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class RouteResponse {
    private UUID id;
    private String name;
    private String description;
    private UUID routeGroupId;
    private String routeGroupName;
    private UUID startStopId;
    private String startStopName;
    private LocationDto startStopLocation;
    private UUID endStopId;
    private String endStopName;
    private LocationDto endStopLocation;
    private Double distanceKm;
    private Integer estimatedDurationMinutes;
    private String direction;
    private List<RouteStopResponse> routeStops;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    @Data
    public static class RouteStopResponse {
        private UUID stopId;
        private String stopName;
        private LocationDto location;
        private Integer stopOrder;
        private Double distanceFromStartKm;
    }
}