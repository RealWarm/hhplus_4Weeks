
package com.hoonterpark.concertmanager.application;

import com.hoonterpark.concertmanager.domain.event.PaidEvent;
import com.hoonterpark.concertmanager.domain.entity.PaymentEntity;
import com.hoonterpark.concertmanager.domain.entity.ReservationEntity;
import com.hoonterpark.concertmanager.domain.service.*;
import com.hoonterpark.concertmanager.presentation.controller.request.PaymentRequest;
import com.hoonterpark.concertmanager.presentation.controller.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentFacade {
    private final UserService userService;
    private final SeatService seatService;
    private final TokenService tokenService;
    private final PaymentService paymentService;
    private final ReservationService reservationService;
    private final ApplicationEventPublisher eventPublisher;


    // 콘서트 결제
    public PaymentResponse makePayment(PaymentRequest request, String token, LocalDateTime now){

        // 토큰 활성화 검증
        tokenService.isActive(token);

        // 예약내역 존재 확인 후 결제완료로 상태 변환
        ReservationEntity reservation = reservationService.payForReservation(request.getReservationId(), now);

        // 유저 잔액확인 후 잔액차감
        userService.payment(reservation.getUserId(), reservation.getTotalPrice());

        // 좌석 확인 후 결제완료로 상태 변환
        seatService.payForSeat(reservation.getSeatId(), now);

        // 결제내역 생성
        PaymentEntity payment = paymentService.makePayment(reservation.getId(), reservation.getTotalPrice());

        // 트랜잭션이 커밋된 후 Kafka로 메세지 전송
        eventPublisher.publishEvent(new PaidEvent(this, reservation));

        return new PaymentResponse(payment.getId());
    }

}