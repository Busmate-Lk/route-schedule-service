package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.request.BusRequest;
import com.busmate.routeschedule.dto.response.BusResponse;
import java.util.List;
import java.util.UUID;

public interface BusService {
    BusResponse createBus(BusRequest request, String userId);
    BusResponse getBusById(UUID id);
    List<BusResponse> getAllBuses();
    BusResponse updateBus(UUID id, BusRequest request, String userId);
    void deleteBus(UUID id);
}
