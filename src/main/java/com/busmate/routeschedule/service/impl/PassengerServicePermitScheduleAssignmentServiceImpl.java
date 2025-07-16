package com.busmate.routeschedule.service.impl;

import com.busmate.routeschedule.dto.request.PassengerServicePermitScheduleAssignmentRequest;
import com.busmate.routeschedule.dto.response.PassengerServicePermitScheduleAssignmentResponse;
import com.busmate.routeschedule.entity.PassengerServicePermit;
import com.busmate.routeschedule.entity.PassengerServicePermitScheduleAssignment;
import com.busmate.routeschedule.entity.Route;
import com.busmate.routeschedule.entity.Schedule;
import com.busmate.routeschedule.enums.StatusEnum;
import com.busmate.routeschedule.exception.ConflictException;
import com.busmate.routeschedule.exception.ResourceNotFoundException;
import com.busmate.routeschedule.repository.PassengerServicePermitRepository;
import com.busmate.routeschedule.repository.PassengerServicePermitScheduleAssignmentRepository;
import com.busmate.routeschedule.repository.ScheduleRepository;
import com.busmate.routeschedule.service.PassengerServicePermitScheduleAssignmentService;
import com.busmate.routeschedule.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerServicePermitScheduleAssignmentServiceImpl implements PassengerServicePermitScheduleAssignmentService {
    private final PassengerServicePermitScheduleAssignmentRepository assignmentRepository;
    private final PassengerServicePermitRepository permitRepository;
    private final ScheduleRepository scheduleRepository;
    private final MapperUtils mapperUtils;

    @Override
    public PassengerServicePermitScheduleAssignmentResponse createAssignment(PassengerServicePermitScheduleAssignmentRequest request, String userId) {
        validateAssignmentRequest(request);
        PassengerServicePermit permit = validateAndGetPermit(request.getPermitId());
        Schedule schedule = validateAndGetSchedule(request.getScheduleId());
        Route route = schedule.getRoute();
        validatePermitScheduleCompatibility(permit, schedule, route);

        if (assignmentRepository.existsByPassengerServicePermitIdAndScheduleIdAndStartDate(
                request.getPermitId(), request.getScheduleId(), request.getStartDate())) {
            throw new ConflictException("Assignment already exists for permit " + request.getPermitId() +
                    ", schedule " + request.getScheduleId() + ", and start date " + request.getStartDate());
        }

        PassengerServicePermitScheduleAssignment assignment = mapToAssignment(request, userId, permit, schedule);
        PassengerServicePermitScheduleAssignment savedAssignment = assignmentRepository.save(assignment);
        return mapToResponse(savedAssignment);
    }

    @Override
    public PassengerServicePermitScheduleAssignmentResponse getAssignmentById(UUID id) {
        PassengerServicePermitScheduleAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));
        return mapToResponse(assignment);
    }

    @Override
    public List<PassengerServicePermitScheduleAssignmentResponse> getAllAssignments() {
        return assignmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PassengerServicePermitScheduleAssignmentResponse updateAssignment(UUID id, PassengerServicePermitScheduleAssignmentRequest request, String userId) {
        PassengerServicePermitScheduleAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));

        validateAssignmentRequest(request);
        PassengerServicePermit permit = validateAndGetPermit(request.getPermitId());
        Schedule schedule = validateAndGetSchedule(request.getScheduleId());
        Route route = schedule.getRoute();
        validatePermitScheduleCompatibility(permit, schedule, route);

        if (!assignment.getPassengerServicePermit().getId().equals(request.getPermitId()) ||
                !assignment.getSchedule().getId().equals(request.getScheduleId()) ||
                !assignment.getStartDate().equals(request.getStartDate())) {
            if (assignmentRepository.existsByPassengerServicePermitIdAndScheduleIdAndStartDate(
                    request.getPermitId(), request.getScheduleId(), request.getStartDate())) {
                throw new ConflictException("Assignment already exists for permit " + request.getPermitId() +
                        ", schedule " + request.getScheduleId() + ", and start date " + request.getStartDate());
            }
        }

        // Store the original ID to avoid overwriting
        UUID originalId = assignment.getId();

        // Update fields manually to avoid ID conflicts
        assignment.setPassengerServicePermit(permit);
        assignment.setSchedule(schedule);
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());
        try {
            assignment.setStatus(StatusEnum.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new ConflictException("Invalid status: " + request.getStatus());
        }

        // Ensure the ID remains unchanged
        assignment.setId(originalId);
        assignment.setUpdatedBy(userId);

        PassengerServicePermitScheduleAssignment updatedAssignment = assignmentRepository.save(assignment);
        return mapToResponse(updatedAssignment);
    }

    @Override
    public void deleteAssignment(UUID id) {
        PassengerServicePermitScheduleAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));

        assignmentRepository.deleteById(id);
    }

    private void validateAssignmentRequest(PassengerServicePermitScheduleAssignmentRequest request) {
        if (request.getEndDate() != null && request.getEndDate().isBefore(request.getStartDate())) {
            throw new ConflictException("End date cannot be before start date");
        }
        try {
            StatusEnum.valueOf(request.getStatus());
        } catch (IllegalArgumentException e) {
            throw new ConflictException("Invalid status: " + request.getStatus());
        }
    }

    private PassengerServicePermit validateAndGetPermit(UUID permitId) {
        return permitRepository.findById(permitId)
                .orElseThrow(() -> new ResourceNotFoundException("Permit not found with id: " + permitId));
    }

    private Schedule validateAndGetSchedule(UUID scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + scheduleId));
    }

    private void validatePermitScheduleCompatibility(PassengerServicePermit permit, Schedule schedule, Route route) {
//        if (!permit.getOperator().getId().equals(schedule.getOperatorId())) {
//            throw new ConflictException("Permit and schedule must belong to the same operator");
//        }

//        if (!permit.getRouteGroup().getId().equals(route.getRouteGroupId())) {
        if (!permit.getRouteGroup().getId().equals(route.getRouteGroup().getId())) {
            throw new ConflictException("Permit and schedule must belong to the same route group");
        }
    }

    private PassengerServicePermitScheduleAssignment mapToAssignment(PassengerServicePermitScheduleAssignmentRequest request, String userId, PassengerServicePermit permit, Schedule schedule) {
        // Manual mapping instead of using MapperUtils to avoid ModelMapper conflicts
        PassengerServicePermitScheduleAssignment assignment = new PassengerServicePermitScheduleAssignment();
        
        // Don't set ID - let JPA generate it
        assignment.setPassengerServicePermit(permit);
        assignment.setSchedule(schedule);
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());
        
        try {
            assignment.setStatus(StatusEnum.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new ConflictException("Invalid status: " + request.getStatus());
        }
        
        assignment.setCreatedBy(userId);
        assignment.setUpdatedBy(userId);
        
        return assignment;
    }

    private PassengerServicePermitScheduleAssignmentResponse mapToResponse(PassengerServicePermitScheduleAssignment assignment) {
        PassengerServicePermitScheduleAssignmentResponse response = mapperUtils.map(assignment, PassengerServicePermitScheduleAssignmentResponse.class);
        response.setPermitId(assignment.getPassengerServicePermit().getId());
        response.setPermitNumber(assignment.getPassengerServicePermit().getPermitNumber());
        response.setScheduleId(assignment.getSchedule().getId());
        response.setScheduleName(assignment.getSchedule().getName());
        return response;
    }
}
