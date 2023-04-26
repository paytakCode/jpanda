package com.kakao.jPanda.kyg.service;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;

public interface ChargeService {

	int addCharge(ChargeDto chargeDto);

	int checkAvailableCoupon(CouponUseDto couponUseDto);

	int getAvailAmountCoupon(CouponUseDto couponUseDto);

	//int findTotalBamboo(String memberId);
	long findTotalBamboo(String memberId);




	
}
