package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.request.PassengerServicePermitRequest;
import com.busmate.routeschedule.dto.response.PassengerServicePermitResponse;
import java.util.List;
import java.util.UUID;

public interface PassengerServicePermitService {
    PassengerServicePermitResponse createPermit(PassengerServicePermitRequest request, String userId);
    PassengerServicePermitResponse getPermitById(UUID id);
    List<PassengerServicePermitResponse> getAllPermits();
    PassengerServicePermitResponse updatePermit(UUID id, PassengerServicePermitRequest request, String userId);
    void deletePermit(UUID id);
}
