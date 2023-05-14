package com.kakao.jPanda.admin.dao;

import java.util.List;

import com.kakao.jPanda.admin.domain.CompanySalesDto;
import com.kakao.jPanda.admin.domain.CouponDto;
import com.kakao.jPanda.admin.domain.ExchangeDto;
import com.kakao.jPanda.admin.domain.NoticeDto;
import com.kakao.jPanda.admin.domain.PaginationDto;
import com.kakao.jPanda.admin.domain.ReportDto;
import com.kakao.jPanda.admin.domain.TalentDto;
import com.kakao.jPanda.admin.domain.TalentRefundDto;

public interface AdminDao {
	//notice
	int updateNotice(NoticeDto notice);
	int insertNotice(NoticeDto notice);
	NoticeDto selectNoticeByNoticeNo(Long noticeNo);
	int selectNoticeForTotalCount();
	List<NoticeDto> selectNoticeByPagination(PaginationDto paginationDto);

	
	//exchange
	List<ExchangeDto> selectExchangeByPagination(PaginationDto paginationDto);
	int updateExchange(ExchangeDto exchange);
	int selectExchangeForTotalCount();
	
	//coupon
	List<CouponDto> selectCouponsByPagination(PaginationDto paginationDto);
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
	List<TalentDto> selectTalentByPagination(PaginationDto paginationDto);
	
	//talent-refund
	int updateTalentRefundToSuccessByPurchaseNosAndStatus(TalentRefundDto paramDto);
	int updateTalentRefundToCompanionByPurchaseNosAndStatus(TalentRefundDto paramDto);
	int selectTalentRefundForTotalCount();
	List<TalentRefundDto> selectTalentRefunByPagination(PaginationDto paginationDto);
	
	//report
	List<ReportDto> selectReport();
	List<ReportDto> selectReportByBlackId(String blackId);
	int updateReportByMemberId(String memberId);

}