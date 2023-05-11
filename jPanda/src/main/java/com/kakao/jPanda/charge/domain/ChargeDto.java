package com.kakao.jPanda.charge.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChargeDto {
	
	private String		chargerId;
	private String		paymentMethod; 
	private Long		paymentAmount;
	private Long		chargeBamboo;
	private Timestamp	chargeDate;
	private String		couponCode;
	
}
