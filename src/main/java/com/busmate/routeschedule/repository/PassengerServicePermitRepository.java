package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.PassengerServicePermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PassengerServicePermitRepository extends JpaRepository<PassengerServicePermit, UUID> {
    boolean existsByPermitNumber(String permitNumber);
}
