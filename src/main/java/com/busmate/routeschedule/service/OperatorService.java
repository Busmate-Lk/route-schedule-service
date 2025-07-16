package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.request.OperatorRequest;
import com.busmate.routeschedule.dto.response.OperatorResponse;
import java.util.List;
import java.util.UUID;

public interface OperatorService {
    OperatorResponse createOperator(OperatorRequest request, String userId);
    OperatorResponse getOperatorById(UUID id);
    List<OperatorResponse> getAllOperators();
    OperatorResponse updateOperator(UUID id, OperatorRequest request, String userId);
    void deleteOperator(UUID id);
}
