package com.busmate.routeschedule.service;

import com.busmate.routeschedule.dto.request.ScheduleRequest;
import com.busmate.routeschedule.dto.response.ScheduleResponse;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest request, String userId);
    List<ScheduleResponse> createBulkSchedules(List<ScheduleRequest> requests, String userId);
    ScheduleResponse getScheduleById(UUID id);
    List<ScheduleResponse> getAllSchedules();
    ScheduleResponse updateSchedule(UUID id, ScheduleRequest request, String userId);
    void deleteSchedule(UUID id);
}