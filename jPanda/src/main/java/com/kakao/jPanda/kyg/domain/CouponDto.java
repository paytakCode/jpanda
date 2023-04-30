package com.kakao.jPanda.kyg.domain;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CouponDto {
	
	private String		couponCode;
	private Timestamp	issueDate;
	private Timestamp	expireDate;
	private Long		couponValue;
}
