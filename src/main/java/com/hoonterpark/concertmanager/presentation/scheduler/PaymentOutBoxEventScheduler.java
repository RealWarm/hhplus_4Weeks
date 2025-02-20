package com.hoonterpark.concertmanager.presentation.scheduler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoonterpark.concertmanager.domain.entity.ReservationEntity;
import com.hoonterpark.concertmanager.domain.enums.PaymentOutBoxEventStatus;
import com.hoonterpark.concertmanager.domain.enums.ReservationStatus;
import com.hoonterpark.concertmanager.domain.kafka.PaymentMessageOutboxRepository;
import com.hoonterpark.concertmanager.domain.repository.ReservationRepository;
import com.hoonterpark.concertmanager.infrastructure.kafka.PaymentOutboxEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentOutBoxEventScheduler {
    @Value("${topic.payment}")
    private String PAYMENT_TOPIC;

    private static final int MAX_RETRY_COUNT = 3;

    private final PaymentMessageOutboxRepository paymentMessageOutboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ReservationRepository reservationRepository;
    private final ObjectMapper objectMapper;



    @Scheduled(fixedRate = 30000)
    public void processPaymentOutboxEvent() {
        List<PaymentOutboxEvent> pendingEvents = paymentMessageOutboxRepository.findByStatus(PaymentOutBoxEventStatus.PENDING.name());

        for (PaymentOutboxEvent event : pendingEvents) {
            try {
                ReservationEntity reservation = reservationRepository.findById(event.getAggregateId())
                        .orElseThrow(() -> new RuntimeException("예약 정보가 존재하지 않습니다."));
                if (ReservationStatus.PAID.equals(reservation.getStatus())) {
                    String payload = objectMapper.writeValueAsString(reservation);
                    kafkaTemplate.send(PAYMENT_TOPIC, String.valueOf(event.getAggregateId()), payload);
                    event.setStatus(PaymentOutBoxEventStatus.RECEIVED.name());
                    paymentMessageOutboxRepository.save(event);
                }
            } catch (Exception e) {
                log.error("카프카 결제정보 이벤트 전송에 실패했습니다: {}", event, e);
                handleEventFailure(event);
            }
        }
    }

    private void handleEventFailure(PaymentOutboxEvent event) {
        int retryCount = event.getRetryCount() + 1;

        if (retryCount >= MAX_RETRY_COUNT) {
            event.setStatus(PaymentOutBoxEventStatus.FAIL.name());
            event.setRetryCount(MAX_RETRY_COUNT);
            log.error("최대 재시도 횟수를 초과하여 이벤트 전송이 실패하였습니다.: {}", event);
        } else {
            event.setRetryCount(retryCount);
        }

        paymentMessageOutboxRepository.save(event);
    }
}