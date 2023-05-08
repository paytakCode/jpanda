package com.kakao.jPanda.kyg.service;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.PaymentDto;
import com.kakao.jPanda.kyg.domain.Pagination;

public interface ChargeService {

	int addCharge(ChargeDto chargeDto);

	int checkAvailableCoupon(CouponUseDto couponUseDto);

	Long getAvailAmountCoupon(CouponUseDto couponUseDto);

	Long findTotalBambooByMemberId(String memberId);

	List<PaymentDto> findPaymentList();

	List<ChargeDto> findBambooChargeList(ChargeDto chargeDto);

	int totalChargeCnt(String chargerId);

	Map<String, Object> findchargeByPagination(Pagination pagination);


}
