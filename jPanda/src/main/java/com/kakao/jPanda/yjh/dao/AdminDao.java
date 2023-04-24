package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.Coupon;
import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.domain.Talent;

public interface AdminDao {
	//notice
	int updateNotice(Notice notice);
	int insertNotice(Notice notice);
	Notice selectNoticeByNoticeNo(Long noticeNo);
	List<Notice> selectNotice();
	
	//exchange
	List<Exchange> selectExchange();
	Exchange selectExchangeByExchangeNo(Long exchangeNo);
	void updateExchange(Exchange exchange);
	
	//coupon
	List<Coupon> findCouponList();
	void insertCoupon(Coupon coupon);
	
	//company-sales
	List<CompanySalesDto> selectCompanySalesAtBambooChargeByYYMMDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesAtBambooChargeByDDDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesAtExchangeByDDDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesAtExchangeByYYMMDate(CompanySalesDto companySalesDto);
	
	//talent
	List<Talent> selectTalent();
}
