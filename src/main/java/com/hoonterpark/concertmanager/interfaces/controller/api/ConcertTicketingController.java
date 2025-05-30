package com.hoonterpark.concertmanager.interfaces.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "콘서트 컨트롤러", description = "콘서트 정보, 토큰, 예약, 결제, 충전 기능 다합쳐 있음 5주차에 분리예정")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ConcertTicketingController {
    
//    private final TokenUseCase tokenUseCase;
//
//
//
//    @PostMapping("/token")
//    @Operation(summary = "신규토큰 발행", description = "콘서트는 한개만 있다 가정")
//    public ResponseEntity<CommonResponse<TokenResponse.Token>> issueToken(
//            @RequestBody UserTokenRequest request
//    ) {
//        log.info("request :: {} ", request);
////        TokenResponse.Token dto = new TokenResponse.Token("TokenValue", 11);
//
//        CommonResponse<TokenResponse.Token> response = new CommonResponse<>();
//        response.setResult("200");
//        response.setMessage("Success");
//        response.setData(tokenUseCase.issueToken(request.getUserId(), LocalDateTime.now()));
//        log.info(response.toString());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }//issueToken
//
//
//    @GetMapping("/token")
//    @Operation(summary = "대기열 조회", description = "")
//    public ResponseEntity<TokenResponse.TokenQueueResponse> getQueueToken(
//            @RequestParam String tokenValue
//    ){
//        return new ResponseEntity<>(new TokenResponse.TokenQueueResponse(TokenStatus.PENDING, 11), HttpStatus.OK);
//    }
//
//
//    @GetMapping("/concerts")
//    @Operation(summary = "예약가능한 콘서트 날짜", description = "")
//    public ResponseEntity<List<ConcertResponse.Concert>> getAvailableConcert(
//
//    ) {
//        ConcertResponse.Concert concert1 = new ConcertResponse.Concert("아이유 새해 콘서트");
//        ConcertResponse.Concert concert2 = new ConcertResponse.Concert("성시경 신년 콘서트");
//        return new ResponseEntity<>(List.of(concert1, concert2), HttpStatus.OK);
//    }
//
//
//    @GetMapping("/{concertId}/available-dates")
//    @Operation(summary = "예약가능한 콘서트 날짜 조회", description = "")
//    public ResponseEntity<List<ConcertDateDTO>> getAvailableDates(
//            @PathVariable Long concertId,
//            @RequestParam String token
//    ) {
//        List<ConcertDateDTO> options = new ArrayList<>();
//        ConcertDateDTO concert1 = new ConcertDateDTO(1L, LocalDateTime.now().plusDays(11));
//        ConcertDateDTO concert2 = new ConcertDateDTO(2L, LocalDateTime.now().plusDays(11));
//
//        return new ResponseEntity<>(List.of(concert1, concert2), HttpStatus.OK);
//    }
//
//
//    @GetMapping("/{concertId}/available-seats")
//    public ResponseEntity<List<SeatDTO>> getAvailableSeats(
//            @PathVariable Long concertOptionId,
//            @RequestParam String token
//    ) {
//        SeatDTO seat1 = new SeatDTO(1L, "A1", "Available");
//        SeatDTO seat2 = new SeatDTO(2L, "A2", "Hold");
//        return new ResponseEntity<>(List.of(seat1, seat2), HttpStatus.OK);
//    }
//
//
//    @PostMapping("/reserves")
//    public ResponseEntity<CommonResponse<ReserveResponse>> reserveSeat(
//            @RequestBody ReservationRequest request
//    ) {
//        CommonResponse<ReserveResponse> response = CommonResponse.<ReserveResponse>builder()
//                .data(new ReserveResponse(1L))
//                .message("Success")
//                .result("200")
//                .build();
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//
//    @PatchMapping("/balance/charge")
//    public ResponseEntity<UserBalanceResponse> chargeBalance(
//            @RequestBody ChargeBalanceRequest request
//    ) {
//        UserBalanceResponse response = new UserBalanceResponse(6000L);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/balance")
//    public ResponseEntity<UserBalanceResponse> getBalance(
//            @RequestParam Long userId
//    ) {
//        UserBalanceResponse response = new UserBalanceResponse(6000L);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//
//    @PostMapping("/pay")
//    public ResponseEntity<CommonResponse<PaymentResponse>> pay(
//            @RequestBody PaymentRequest request
//    ) {
//        CommonResponse<PaymentResponse> response = new CommonResponse<>();
//        response.setResult("200");
//        response.setMessage("Success");
//        response.setData(new PaymentResponse(456L));
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


}//end


