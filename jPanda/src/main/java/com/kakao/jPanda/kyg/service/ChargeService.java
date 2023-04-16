package com.kakao.jPanda.kyg.service;

import com.kakao.jPanda.kyg.domain.ChargeDto;

public interface ChargeService {

	int insertCharge(ChargeDto chargeDto);

//	시큐리티 에러
	

	int checkCoupon(String couponNo);
	
	
	/*
	쿠폰 적용가능 체크 로직
	boolean couponCheck(String couponNo);
	*/
}
