package com.hoonterpark.concertmanager.domain.event;


import com.hoonterpark.concertmanager.domain.entity.ReservationEntity;
import org.springframework.context.ApplicationEvent;


public class PaidEvent extends ApplicationEvent {
    private final ReservationEntity reservation;

    public PaidEvent(Object source, ReservationEntity reservation) {
        super(source);
        this.reservation = reservation;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

}