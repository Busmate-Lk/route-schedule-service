package com.busmate.routeschedule.entity;

import com.busmate.routeschedule.enums.RequestStatusEnum;
import com.busmate.routeschedule.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "bus_passenger_service_permit_assignment")
public class BusPassengerServicePermitAssignment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "passenger_service_permit_id", nullable = false)
    private PassengerServicePermit passengerServicePermit;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatusEnum requestStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum status;
}
