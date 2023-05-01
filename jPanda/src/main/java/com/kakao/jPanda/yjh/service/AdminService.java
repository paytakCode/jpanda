package com.kakao.jPanda.yjh.service;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

public interface AdminService {
	//notice
	List<NoticeDto> findNotice();
	NoticeDto findNoticeByNoticeNo(String noticeNo);
	String modifyNotice(NoticeDto notice);
	String addNotice(NoticeDto notice);
	
	//exchange
	List<ExchangeDto> findExchange();
	String modifyExchangeStatusByExchangeNos(String[] exchangeNoArray, String status);
	
	//coupon
	List<CouponDto> findCouponsExpired();
	String generateCouponNo();
	Map<String, Integer> addCoupon(CouponDto couponDto);
	
	//company-sales
	List<CompanySalesDto> findCompanySalesByStartDateAndEndDate(String startDate, String endDate);
	
	//talent
	List<TalentDto> findTalent();
	String modifyTalentBySellerIds(List<String> sellerId);
	
	//talent-refund
	List<TalentRefundDto> findTalentRefund();
	int modifyTalentRefundByPurchaseNosAndStatus(List<TalentRefundDto> talentRefundDto);

}
