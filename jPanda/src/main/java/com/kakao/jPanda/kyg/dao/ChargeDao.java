package com.kakao.jPanda.kyg.dao;

import java.util.List;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.PaymentDto;

public interface ChargeDao {

	int insertCouponUse(ChargeDto chargeDto);
	
	int insertCharge(ChargeDto chargeDto);

	double selectBonusRatio(ChargeDto chargeDto);

	CouponUseDto selectCouponUse(CouponUseDto couponUseDto);

	Long selectAvailAmountCoupon(CouponUseDto couponUseDto);

	CouponDto selectCouponByCouponCode(String couponCode);

	Long selectChargeBambooAmount(String memberId);

	Long selectBambooUseAmount(String memberId);

	Long selectTalentRefundAmount(String memberId);

	List<PaymentDto> selectPaymentList(PaymentDto selectMethodBonusDto);

	
	
}
