package com.kakao.jPanda.kyg.domain;

import lombok.Data;

@Data
public class ChargeDto {
	
	private String	chargerId;
	private String	couponCode;
	private Long	chargeMoney;	
	private String	paymentMethod; 
	private Long	chargeBamboo;
	
	
	
}
