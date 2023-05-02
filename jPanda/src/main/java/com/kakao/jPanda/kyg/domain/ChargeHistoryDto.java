package com.kakao.jPanda.kyg.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChargeHistoryDto {
	
	private	String		chargerId;
	private Timestamp	chargeDate;
	private String		paymentMethod;
	private Long		paymentAmount;
	private int			chargeBamboo;
	
}
