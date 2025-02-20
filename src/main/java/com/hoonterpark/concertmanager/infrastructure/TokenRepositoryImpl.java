package com.hoonterpark.concertmanager.infrastructure;

import com.hoonterpark.concertmanager.domain.entity.TokenEntity;
import com.hoonterpark.concertmanager.domain.enums.TokenStatus;
import com.hoonterpark.concertmanager.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//@Repository
//@RequiredArgsConstructor
//public class TokenRepositoryImpl implements TokenRepository {
//    private final TokenJpaRepository tokenJpaRepository;
//
//
//    @Override
//    public TokenEntity save(TokenEntity newToken) {
//        return tokenJpaRepository.save(newToken);
//    }
//
//
//    @Override
//    public List<TokenEntity> saveAll(List<TokenEntity> newTokens) {
//        return tokenJpaRepository.saveAll(newTokens);
//    }
//
//
//    @Override
//    public Optional<TokenEntity> findByTokenValue(String tokenValue) {
//        return tokenJpaRepository.findByTokenValue(tokenValue);
//    }
//
//
//    @Override
//    public Optional<TokenEntity> findLatestActiveToken() {
//        return tokenJpaRepository.findLatestActiveToken(PageRequest.of(0, 1));
//    }
//
//
//    @Override
//    public List<TokenEntity> findByStatusIn(List<TokenStatus> statuses) {
//        return tokenJpaRepository.findByStatusIn(statuses);
//    }
//
//
//    // PENDING 상태의 토큰 중에서 가장 오래된 N개의 토큰을 조회
//    // PENDING > ACTIVE 할 토큰 limit개 가져온다.
//    @Override
//    public List<TokenEntity> findTokensToActivate(int limit) {
//        // Pageable 객체 생성
//        Pageable pageable = PageRequest.of(0, limit);
//        // 활성화 할 대기중인 토큰 조회(대기한지 오래된 토큰 limit개 가져오기)
//        return tokenJpaRepository.findTokensToActivate(pageable).getContent();
//    }
//
//}
