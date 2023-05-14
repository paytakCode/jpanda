package com.kakao.jPanda.charge.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CouponDto {
	
	private String		couponCode;
	private Timestamp	issueDate;
	private Timestamp	expireDate;
	private Long		couponValue;
}
