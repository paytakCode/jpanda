package com.kakao.jPanda.charge.service;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.charge.domain.ChargeDto;
import com.kakao.jPanda.charge.domain.CouponUseDto;
import com.kakao.jPanda.charge.domain.PaginationDto;
import com.kakao.jPanda.charge.domain.PaymentDto;

public interface ChargeService {

	int addCharge(ChargeDto chargeDto);

	int checkAvailableCoupon(CouponUseDto couponUseDto);

	Long getAvailAmountCoupon(CouponUseDto couponUseDto);

	Long findTotalBambooByMemberId(String memberId);

	List<PaymentDto> findPaymentList();

	int totalChargeCntByChargerId(String chargerId);

	Map<String, Object> findchargeHistoryMapByPagination(PaginationDto paginationDto);

}
