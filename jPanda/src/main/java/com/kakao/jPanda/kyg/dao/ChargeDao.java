package com.kakao.jPanda.kyg.dao;

import com.kakao.jPanda.kyg.domain.ChargeDto;

public interface ChargeDao {

	int insertCharge(ChargeDto chargeDto);

//	시큐리티 에러
	/*
	boolean couponCheck(String couponNo);
*/
	
	
	
	
	
	
	/*
	쿠폰 적용가능 체크 로직
	List<String> selectCoupon(String couponNo);
	 */
}
