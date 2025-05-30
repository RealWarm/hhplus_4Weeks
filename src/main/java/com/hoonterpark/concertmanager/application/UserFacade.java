package com.hoonterpark.concertmanager.application;

import com.hoonterpark.concertmanager.domain.entity.UserEntity;
import com.hoonterpark.concertmanager.domain.service.UserService;
import com.hoonterpark.concertmanager.interfaces.controller.api.response.UserBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    // 고객 정보조회 == 포인트 조회
    public UserBalanceResponse getUserBalance(Long userId){
        UserEntity user = userService.findById(userId);
        return new UserBalanceResponse(user.getPoint());
    }


    // 포인트 충전
    public UserBalanceResponse chargeUserPoint(Long userId, Long amount){
        UserEntity user = userService.chargePoint(userId, amount);
        return new UserBalanceResponse(user.getPoint());
    }

}//end
