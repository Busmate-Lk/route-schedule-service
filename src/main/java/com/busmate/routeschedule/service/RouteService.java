package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.response.RouteResponse;
import java.util.List;
import java.util.UUID;

public interface RouteService {
    RouteResponse getRouteById(UUID id);
    List<RouteResponse> getAllRoutes();
}