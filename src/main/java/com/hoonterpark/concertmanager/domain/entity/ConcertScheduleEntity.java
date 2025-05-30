package com.hoonterpark.concertmanager.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@Table(name = "ConcertSchedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long concertId;

    @Column(nullable = false)
    private LocalDateTime performanceDay;

    
    // 단위 테스트용
    @Builder
    public ConcertScheduleEntity(Long id, Long concertId, LocalDateTime performanceDay) {
        this.id = id;
        this.concertId = concertId;
        this.performanceDay = performanceDay;
    }

    @Builder
    public ConcertScheduleEntity(Long concertId, LocalDateTime performanceDay) {
        this.concertId = concertId;
        this.performanceDay = performanceDay;
    }

    public Boolean isAvailable(LocalDateTime now){
        if(performanceDay.isBefore(now)){
            return true;
        }
        return false;
    }//isAvailable


}
