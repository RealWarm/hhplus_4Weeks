package com.hoonterpark.concertmanager.domain.listener;

import com.hoonterpark.concertmanager.domain.event.PaidEvent;
import com.hoonterpark.concertmanager.infrastructure.kafka.KafkaPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaidEventListener {
    private final KafkaPublisher kafkaPublisher;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaidEvent(PaidEvent paidEvent) {
        try {
            kafkaPublisher.publishPayment(paidEvent.getReservation());
        } catch (Exception e) {
            log.error("예약 정보 전달에 실패하였습니다. 예약 정보: {}", paidEvent.getReservation(), e);
        }
    }

}