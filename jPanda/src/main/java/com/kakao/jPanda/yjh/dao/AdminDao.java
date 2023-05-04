package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.Pagination;
import com.kakao.jPanda.yjh.domain.ReportDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

public interface AdminDao {
	//notice
	int updateNotice(NoticeDto notice);
	int insertNotice(NoticeDto notice);
	NoticeDto selectNoticeByNoticeNo(Long noticeNo);
	List<NoticeDto> selectNotice();
	int selectNoficeByPagination();
	
	//exchange
	List<ExchangeDto> selectExchange();
	ExchangeDto selectExchangeByExchangeNo(Long exchangeNo);
	int updateExchange(ExchangeDto exchange);
	
	//coupon
	List<CouponDto> selectCouponsExpired();
	List<CouponDto> findCouponList();
	int insertCoupon(CouponDto couponDto);
	
	//company-sales
	List<CompanySalesDto> selectCompanySalesByYYYYDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesByMMDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesByDDDate(CompanySalesDto companySalesDto);

	
	//talent
	List<TalentDto> selectTalent();
	int updateTalentByTalentNos(TalentDto paramDto);
	TalentDto selectTalentByTalentNo(Long talentNo);
	
	//talent-refund
	List<TalentRefundDto> selectTalentRefund();
	int updateTalentRefundToSuccessByPurchaseNosAndStatus(TalentRefundDto paramDto);
	int updateTalentRefundToCompanionByPurchaseNosAndStatus(TalentRefundDto paramDto);
	
	//report
	List<ReportDto> selectReport();
	List<ReportDto> selectReportByBlackId(String blackId);
	int updateReportByMemberId(String memberId);

}