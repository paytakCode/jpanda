package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

public interface AdminDao {
	//notice
	int updateNotice(NoticeDto notice);
	int insertNotice(NoticeDto notice);
	NoticeDto selectNoticeByNoticeNo(Long noticeNo);
	List<NoticeDto> selectNotice();
	
	//exchange
	List<ExchangeDto> selectExchange();
	ExchangeDto selectExchangeByExchangeNo(Long exchangeNo);
	void updateExchange(ExchangeDto exchange);
	
	//coupon
	List<CouponDto> findCouponList();
	void insertCoupon(CouponDto coupon);
	
	//company-sales
	List<CompanySalesDto> selectCompanyByDDDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesByYYMMDate(CompanySalesDto companySalesDto);
	
	//talent
	List<TalentDto> selectTalent();
	int updateTalentBySellerIds(List<String> sellerId);
	
	//talent-refund
	List<TalentRefundDto> selectTalentRefund();
}
