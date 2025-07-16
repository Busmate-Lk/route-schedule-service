//package com.busmate.routeschedule.repository;
//
//import com.busmate.routeschedule.entity.Schedule;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    boolean existsByRouteId(Long routeId);
//}

package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    boolean existsByNameAndRoute_Id(String name, UUID routeId);
}