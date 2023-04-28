package com.kakao.jPanda.kyg.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CouponDto {
	
	private String		couponNo;
	private LocalDate	issueDate;
	private LocalDate	expireDate;
	private Long		couponValue;
}
