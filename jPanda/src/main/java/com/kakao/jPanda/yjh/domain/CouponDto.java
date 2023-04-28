package com.kakao.jPanda.yjh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponDto {
	private String couponNo;
	private String issueDate;
	private String expireDate;
	private Long couponValue;
}
