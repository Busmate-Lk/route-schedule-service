package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, UUID> {
    boolean existsByName(String name);
}
