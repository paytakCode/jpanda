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
	List<NoticeDto> findNotice(Pagination pagination);
	NoticeDto findNoticeByNoticeNo(String noticeNo);
	String modifyNotice(NoticeDto notice);
	String addNotice(NoticeDto notice);
	int findNoticeForPagination();
	
	//exchange
	Map<String, Object> findExchange(Pagination pagination);
	int modifyExchangeStatusByExchangeNos(List<ExchangeDto> exchangeDto);
	int findExchangeByPagination(Pagination pagination);
	
	//coupon
	
	String generateCouponNo();
	Map<String, Object> findCouponListByPagination(Pagination pagination);
	Map<String, Integer> addCoupon(CouponDto couponDto);
	
	//company-sales
	List<CompanySalesDto> findCompanySalesByStartDateAndEndDate(Timestamp startDate, Timestamp endDate, String periodicData);
	
	//talent
	List<TalentDto> findTalent();
	Map<String, Object> findtalentByPagination(Pagination pagination);
	int modifyTalentByTalentNos(List<TalentDto> talentDto);
	TalentDto findTalentByTalentNo(Long talentNo);
	
	//talent-refund
	List<TalentRefundDto> findTalentRefund();
	Map<String, Object> findTalentRefundByPagination(Pagination pagination);
	int modifyTalentRefundByPurchaseNosAndStatus(List<TalentRefundDto> talentRefundDto);
	
	//report
	List<ReportDto> findReport();
	List<ReportDto> findReportByBlackId(String blackId);
	int modifyReportByMemberId(String memberId);

}
