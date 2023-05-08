package com.kakao.jPanda.yjh.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.Pagination;
import com.kakao.jPanda.yjh.domain.ReportDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

public interface AdminService {
	//notice
	Map<String, Object> findNoticeByPagination(Pagination pagination);
	NoticeDto findNoticeByNoticeNo(String noticeNo);
	String modifyNotice(NoticeDto notice);
	String addNotice(NoticeDto notice);
	
	//exchange
	Map<String, Object> findExchangeByPagination(Pagination pagination);
	int modifyExchangeStatusByExchangeNos(List<ExchangeDto> exchangeDto);
	
	//coupon
	String generateCouponNo();
	Map<String, Object> findCouponListByPagination(Pagination pagination);
	Map<String, Integer> addCoupon(CouponDto couponDto);
	
	//company-sales
	List<CompanySalesDto> findCompanySalesByStartDateAndEndDate(Timestamp startDate, Timestamp endDate, String periodicData);
	
	//talent
	Map<String, Object> findTalentByPagination(Pagination pagination);
	int modifyTalentByTalentNos(List<TalentDto> talentDto);
	TalentDto findTalentByTalentNo(Long talentNo);
	
	//talent-refund
	Map<String, Object> findTalentRefundByPagination(Pagination pagination);
	int modifyTalentRefundByPurchaseNosAndStatus(List<TalentRefundDto> talentRefundDto);
	
	//report
	List<ReportDto> findReport();
	List<ReportDto> findReportByBlackId(String blackId);
	int modifyReportByMemberId(String memberId);

}
