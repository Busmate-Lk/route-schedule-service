package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StopRepository extends JpaRepository<Stop, UUID> {
    boolean existsByNameAndLocation_City(String name, String city);
}

