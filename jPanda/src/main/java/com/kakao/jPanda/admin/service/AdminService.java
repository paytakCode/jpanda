package com.kakao.jPanda.admin.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.kakao.jPanda.admin.domain.CompanySalesDto;
import com.kakao.jPanda.admin.domain.CouponDto;
import com.kakao.jPanda.admin.domain.ExchangeDto;
import com.kakao.jPanda.admin.domain.NoticeDto;
import com.kakao.jPanda.admin.domain.PaginationDto;
import com.kakao.jPanda.admin.domain.ReportDto;
import com.kakao.jPanda.admin.domain.TalentDto;
import com.kakao.jPanda.admin.domain.TalentRefundDto;

public interface AdminService {
	//notice
	Map<String, Object> findNoticeByPagination(PaginationDto paginationDto);
	NoticeDto findNoticeByNoticeNo(String noticeNo);
	String modifyNotice(NoticeDto notice);
	String addNotice(NoticeDto notice);
	
	//exchange
	Map<String, Object> findExchangeByPagination(PaginationDto paginationDto);
	int modifyExchangeStatusByExchangeNos(List<ExchangeDto> exchangeDto);
	
	//coupon
	String generateCouponNo();
	Map<String, Object> findCouponListByPagination(PaginationDto paginationDto);
	Map<String, Integer> addCoupon(CouponDto couponDto);
	
	//company-sales
	List<CompanySalesDto> findCompanySalesByStartDateAndEndDate(Timestamp startDate, Timestamp endDate, String periodicData);
	
	//talent
	Map<String, Object> findTalentByPagination(PaginationDto paginationDto);
	int modifyTalentByTalentNos(List<TalentDto> talentDto);
	TalentDto findTalentByTalentNo(Long talentNo);
	
	//talent-refund
	Map<String, Object> findTalentRefundByPagination(PaginationDto paginationDto);
	int modifyTalentRefundByPurchaseNosAndStatus(List<TalentRefundDto> talentRefundDto);
	
	//report
	List<ReportDto> findReport();
	List<ReportDto> findReportByBlackId(String blackId);
	int modifyReportByMemberId(String memberId);

}
