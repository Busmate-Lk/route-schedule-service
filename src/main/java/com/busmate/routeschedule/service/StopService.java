package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.request.StopRequest;
import com.busmate.routeschedule.dto.response.StopResponse;
import java.util.List;
import java.util.UUID;

public interface StopService {
    StopResponse createStop(StopRequest request, String userId);
    StopResponse getStopById(UUID id);
    List<StopResponse> getAllStops();
    StopResponse updateStop(UUID id, StopRequest request, String userId);
    void deleteStop(UUID id);
}