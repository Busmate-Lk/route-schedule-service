package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.request.PassengerServicePermitScheduleAssignmentRequest;
import com.busmate.routeschedule.dto.response.PassengerServicePermitScheduleAssignmentResponse;
import java.util.List;
import java.util.UUID;

public interface PassengerServicePermitScheduleAssignmentService {
    PassengerServicePermitScheduleAssignmentResponse createAssignment(PassengerServicePermitScheduleAssignmentRequest request, String userId);
    PassengerServicePermitScheduleAssignmentResponse getAssignmentById(UUID id);
    List<PassengerServicePermitScheduleAssignmentResponse> getAllAssignments();
    PassengerServicePermitScheduleAssignmentResponse updateAssignment(UUID id, PassengerServicePermitScheduleAssignmentRequest request, String userId);
    void deleteAssignment(UUID id);
}
