package com.kakao.jPanda.kyg.domain;

import lombok.Data;

@Data
public class PaymentDto {
	
	private String method;
	private double bonusRatio;
}
