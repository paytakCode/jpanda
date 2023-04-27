package com.kakao.jPanda.kyg.domain;

import lombok.Data;


//	쿠폰 적용가능 체크 로직
@Data
public class CouponUseDto {

	String	id;
	String	couponNo;
	int		couponValue;	// 쿠폰값
	int		result;			// 쿠폰사용유무 리턴결과값
}
