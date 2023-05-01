package com.kakao.jPanda.kyg.domain;

import lombok.Data;

@Data
public class CouponUseDto {

	private String	memberId;
	private String	couponCode;
	private Long	couponValue;	
	private int		result;			// 쿠폰사용유무 리턴결과값
}
