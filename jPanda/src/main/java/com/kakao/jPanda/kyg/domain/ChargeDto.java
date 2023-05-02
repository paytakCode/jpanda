package com.kakao.jPanda.kyg.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChargeDto {
	
//	private String	chargerId;
//	private String	couponCode;
//	private Long	chargeMoney;	
//	private String	paymentMethod; 
//	private Long	chargeBamboo;
	
	private String		chargerId;
	private String		paymentMethod; 
//	private Long		chargeMoney;	
	private Long		paymentAmount;
	private Long		chargeBamboo;
	private Timestamp	chargeDate;
	private String		couponCode;
	
	
}
