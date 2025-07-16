package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.ScheduleStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleStopRepository extends JpaRepository<ScheduleStop, UUID> {
}