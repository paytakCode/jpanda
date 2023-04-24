package com.kakao.jPanda.kyg.dao;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;

public interface ChargeDao {

	int insertCouponUse(ChargeDto chargeDto);
	
	int insertCharge(ChargeDto chargeDto);

	double selectBonusRatio(ChargeDto chargeDto);

	CouponUseDto selectCouponUse(CouponUseDto couponUseDto);

	Long selectAvailAmountCoupon(CouponUseDto couponUseDto);

	CouponDto selectCouponByCouponNo(String couponNo);




	

	
	
	
}
