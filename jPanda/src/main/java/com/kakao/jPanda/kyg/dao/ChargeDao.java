package com.kakao.jPanda.kyg.dao;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.bsm.domain.PagerDto;
import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.Pagination;
import com.kakao.jPanda.kyg.domain.PaymentDto;

public interface ChargeDao {

	int insertCouponUse(ChargeDto chargeDto);
	
	int insertCharge(ChargeDto chargeDto);

	CouponUseDto selectCouponUse(CouponUseDto couponUseDto);

	Long selectAvailAmountCoupon(CouponUseDto couponUseDto);

	CouponDto selectCouponByCouponCode(String couponCode);

	Long selectChargeBambooAmountByMemberId(String memberId);

	Long selectBambooUseAmountByMemberId(String memberId);

	Long selectTalentRefundAmountByMemberId(String memberId);

	List<PaymentDto> selectPaymentList();

	List<ChargeDto> selectBambooChargeList(ChargeDto chargeDto);

	int totalChargeCnt(String chargerId);

	List<ChargeDto> selectChargeByPagination(Pagination pagination);


	
}
