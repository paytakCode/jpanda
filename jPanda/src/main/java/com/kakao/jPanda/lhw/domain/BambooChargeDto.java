package com.kakao.jPanda.lhw.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BambooChargeDto {
	private String chargerId;
	
	// 구매 회원 잔여 포인트 조회용 및 값
	private Long chargeBamboo;
	private Long resultBamboo;
}
