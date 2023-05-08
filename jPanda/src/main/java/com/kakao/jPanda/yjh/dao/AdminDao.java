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
	int selectNoticeForTotalCount();
	List<NoticeDto> selectNoticeByPagination(Pagination pagination);

	
	//exchange
	List<ExchangeDto> selectExchangeByPagination(Pagination pagination);
	int updateExchange(ExchangeDto exchange);
	int selectExchangeForTotalCount();
	
	//coupon
	List<CouponDto> selectCouponsByPagination(Pagination pagination);
	List<CouponDto> selectCouponList();
	int insertCoupon(CouponDto couponDto);
	int selectCouponForTotalCount();
	
	//company-sales
	List<CompanySalesDto> selectCompanySalesByYYYYDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesByMMDate(CompanySalesDto companySalesDto);
	List<CompanySalesDto> selectCompanySalesByDDDate(CompanySalesDto companySalesDto);

	
	//talent
	int updateTalentByTalentNos(TalentDto paramDto);
	TalentDto selectTalentByTalentNo(Long talentNo);
	int selectTalentForTotalCount();
	List<TalentDto> selectTalentByPagination(Pagination pagination);
	
	//talent-refund
	int updateTalentRefundToSuccessByPurchaseNosAndStatus(TalentRefundDto paramDto);
	int updateTalentRefundToCompanionByPurchaseNosAndStatus(TalentRefundDto paramDto);
	int selectTalentRefundForTotalCount();
	List<TalentRefundDto> selectTalentRefunByPagination(Pagination pagination);
	
	//report
	List<ReportDto> selectReport();
	List<ReportDto> selectReportByBlackId(String blackId);
	int updateReportByMemberId(String memberId);

}