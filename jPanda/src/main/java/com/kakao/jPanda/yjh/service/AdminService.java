package com.kakao.jPanda.yjh.service;

import java.util.List;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.domain.Talent;

public interface AdminService {
	//notice
	List<Notice> findNotice();
	Notice findNoticeByNoticeNo(String noticeNo);
	String modifyNotice(Notice notice);
	String addNotice(Notice notice);
	
	//exchange
	List<Exchange> findExchange();
	void modifyExchangeStatusByExchangeNos(String[] exchangeNoArray, String status);
	
	//coupon
	String generateCouponNo();
	void addCoupon(String couponValue, String couponNo);
	
	//company-sales
	List<CompanySalesDto> findCompanySalesAtBambooChargeByStartDateAndEndDate(String startDate, String endDate);
	
	//talent
	List<Talent> findTalent();

	
	//	void modifyExchangeByExchangeNos(String[] exchangeNo, String status);
}
