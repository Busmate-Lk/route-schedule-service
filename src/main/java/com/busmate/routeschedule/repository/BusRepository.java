package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusRepository extends JpaRepository<Bus, UUID> {
    boolean existsByNtcRegistrationNumber(String ntcRegistrationNumber);
    boolean existsByPlateNumber(String plateNumber);
}
