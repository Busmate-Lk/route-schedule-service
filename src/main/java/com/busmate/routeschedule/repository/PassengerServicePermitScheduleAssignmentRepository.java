package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.PassengerServicePermitScheduleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PassengerServicePermitScheduleAssignmentRepository extends JpaRepository<PassengerServicePermitScheduleAssignment, UUID> {
    boolean existsByPassengerServicePermitIdAndScheduleIdAndStartDate(UUID permitId, UUID scheduleId, LocalDate startDate);
}
