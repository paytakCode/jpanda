package com.kakao.jPanda.kyg.service;

import java.util.List;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.ChargeHistoryDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.PaymentDto;

public interface ChargeService {

	int addCharge(ChargeDto chargeDto);

	int checkAvailableCoupon(CouponUseDto couponUseDto);

	Long getAvailAmountCoupon(CouponUseDto couponUseDto);

	Long findTotalBamboo(String memberId);

	List<PaymentDto> findPaymentList(PaymentDto paymentDto);

	List<ChargeHistoryDto> findChargeHistoryList(ChargeHistoryDto chargeHistoryListDto);




	
}
