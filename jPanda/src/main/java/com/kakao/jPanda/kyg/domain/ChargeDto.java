package com.kakao.jPanda.kyg.domain;

import lombok.Data;

@Data
public class ChargeDto {
	
	
	private String chargerId;
	private String couponNo;
	private Long chargeMoney;	// PAYMENT_AMOUNT
	private String paymentMethod; // 결제방법
	private Long chargeBamboo;
	
	
//	private String memberId; // 로그인 테스트
	
}
