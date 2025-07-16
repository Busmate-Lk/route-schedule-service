package com.busmate.routeschedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class RouteGroupRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private List<RouteRequest> routes;

    @Data
    public static class RouteRequest {
        private UUID id; // Changed to UUID
        
        @NotBlank(message = "Name is mandatory")
        private String name;

        private String description;

        @NotNull(message = "Start stop ID is mandatory")
        private UUID startStopId;

        @NotNull(message = "End stop ID is mandatory")
        private UUID endStopId;

        private Double distanceKm;

        private Integer estimatedDurationMinutes;

        @NotNull(message = "Direction is mandatory")
        private String direction;

        private List<RouteStopRequest> routeStops;

        @Data
        public static class RouteStopRequest {
            private UUID id; // Changed to UUID
            
            @NotNull(message = "Stop ID is mandatory")
            private UUID stopId;

            @NotNull(message = "Stop order is mandatory")
            private Integer stopOrder;

            private Double distanceFromStartKm;
        }
    }
}