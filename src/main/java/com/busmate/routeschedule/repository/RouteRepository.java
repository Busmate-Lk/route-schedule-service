package com.busmate.routeschedule.repository;

import com.busmate.routeschedule.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {
    boolean existsByNameAndRouteGroup_Id(String name, UUID routeGroupId);
}