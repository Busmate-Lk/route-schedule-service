package com.busmate.routeschedule.service.impl;

import com.busmate.routeschedule.dto.response.RouteResponse;
import com.busmate.routeschedule.entity.Route;
import com.busmate.routeschedule.entity.Stop;
import com.busmate.routeschedule.exception.ResourceNotFoundException;
import com.busmate.routeschedule.repository.RouteRepository;
import com.busmate.routeschedule.repository.StopRepository;
import com.busmate.routeschedule.service.RouteService;
import com.busmate.routeschedule.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final MapperUtils mapperUtils;

    @Override
    public RouteResponse getRouteById(UUID id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + id));
        return mapToResponse(route);
    }

    @Override
    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RouteResponse mapToResponse(Route route) {
        RouteResponse response = mapperUtils.map(route, RouteResponse.class);
        response.setRouteGroupId(route.getRouteGroup().getId());
        response.setRouteGroupName(route.getRouteGroup().getName());

        Stop startStop = stopRepository.findById(route.getStartStopId()).orElse(null);
        if (startStop != null) {
            response.setStartStopName(startStop.getName());
            response.setStartStopLocation(mapperUtils.map(startStop.getLocation(), com.busmate.routeschedule.dto.common.LocationDto.class));
        }

        Stop endStop = stopRepository.findById(route.getEndStopId()).orElse(null);
        if (endStop != null) {
            response.setEndStopName(endStop.getName());
            response.setEndStopLocation(mapperUtils.map(endStop.getLocation(), com.busmate.routeschedule.dto.common.LocationDto.class));
        }

        if (route.getRouteStops() != null) {
            List<RouteResponse.RouteStopResponse> routeStopResponses = route.getRouteStops().stream().map(rs -> {
                RouteResponse.RouteStopResponse rsResponse = new RouteResponse.RouteStopResponse();
                rsResponse.setStopId(rs.getStop().getId());
                rsResponse.setStopName(rs.getStop().getName());
                rsResponse.setLocation(mapperUtils.map(rs.getStop().getLocation(), com.busmate.routeschedule.dto.common.LocationDto.class));
                rsResponse.setStopOrder(rs.getStopOrder());
                rsResponse.setDistanceFromStartKm(rs.getDistanceFromStartKm());
                return rsResponse;
            }).collect(Collectors.toList());
            response.setRouteStops(routeStopResponses);
        }

        return response;
    }
}