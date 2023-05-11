package com.kakao.jPanda.charge.domain;

import lombok.Data;

@Data
public class PaymentDto {
	
	private String method;
	private double bonusRatio;
}
