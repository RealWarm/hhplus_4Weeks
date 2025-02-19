package com.hoonterpark.concertmanager.domain.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoonterpark.concertmanager.domain.entity.ReservationEntity;
import com.hoonterpark.concertmanager.domain.enums.PaymentOutBoxEventStatus;
import com.hoonterpark.concertmanager.infrastructure.kafka.payment.PaymentOutboxEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisher {
    @Value("${topic.payment}")
    private String paymentTopic;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final PaymentMessageOutboxWritter paymentMessageOutboxWritter;


    public void publishPayment(ReservationEntity reservation){
        try {
            String message = objectMapper.writeValueAsString(reservation);
            String partition = String.valueOf(reservation.getId());
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(paymentTopic, partition, message);

            // 전송 성공 또는 실패를 처리하는 코드 추가
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    // 메시지 전송 성공
                    log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
                    PaymentOutboxEvent paymentOutboxEvent = new PaymentOutboxEvent("Reservation", reservation.getId(), "PaidEvent");
                    paymentOutboxEvent.setStatus(PaymentOutBoxEventStatus.INIT.name());
                    paymentMessageOutboxWritter.save(paymentOutboxEvent);
                } else {
                    // 메시지 전송 실패
                    log.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                    PaymentOutboxEvent paymentOutboxEvent = new PaymentOutboxEvent("Reservation", reservation.getId(), "PaidEvent");
                    paymentOutboxEvent.setStatus(PaymentOutBoxEventStatus.PENDING.name());
                    paymentMessageOutboxWritter.save(paymentOutboxEvent);
                }
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("예약 정보 전달에 실패하였습니다. 예약 정보: " + reservation, e);
        }
    }

}
