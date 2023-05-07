package com.kakao.jPanda.yjh.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.AdminDao;
import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.Pagination;
import com.kakao.jPanda.yjh.domain.ReportDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final AdminDao adminDao;
	
	//notice
	/*
	 * 	insert결과에 따라 view에 alert띄우는 기능
	 */
	@Override
	public String addNotice(NoticeDto notice) {
		log.info("Service addNotice() start");
		String resultStr = "";
		
		int result = adminDao.insertNotice(notice);
		
		if(result > 0) {
			resultStr = "<script>alert('공지사항 적용이 완료되었습니다.'); location.href='/admin/notice';</script>";
		} else {
			resultStr = "<script>alert('공지사항 적용 오류'); history.back();</script>";
		}
		return resultStr;
	}

	@Override
	public List<NoticeDto> findNotice(Pagination pagination) {
		log.info("Service findNotice() start");	
		List<NoticeDto> noticeList = adminDao.selectNotice(pagination);
		
		return noticeList;
	}
	
	@Override
	public int findNoticeForPagination() {
		int totalCount = adminDao.selectNoticeByPagination();
		return totalCount;
	}
	
	/*
	 * 	noticeNo에 맞는 noticeDetail조회 기능
	 * 	parameter 타입이 Long이기 때문에 Long타입 parsing후 넘김
	 */
	@Override
	public NoticeDto findNoticeByNoticeNo(String noticeNo) {
		log.info("Service findNoticeByNoticeNo() start");
		NoticeDto notice = adminDao.selectNoticeByNoticeNo(Long.parseLong(noticeNo));
		
		return notice;
	}

	/*
	 * 	noticeDetail에서 수정버튼을 눌러 수정 후 성공시 화면에 alert띄우는 기능
	 */
	@Override
	public String modifyNotice(NoticeDto notice) {
		log.info("Service modifyNotice() start");
		String resultStr = "";
		
		int result = adminDao.updateNotice(notice);
		
		if(result > 0) {
			resultStr = "<script>alert('공지사항 수정이 완료되었습니다.'); location.href='/admin/notice'; </script>";
		} else {
			resultStr = "<script>alert('공지사항 수정 오류'); history.back(); </script>";
		}
		return resultStr;
	}
	
	//exchange
	@Override
	public Map<String, Object> findExchange(Pagination pagination) {
		Map<String, Object> returMap = new HashMap<String, Object>();
		
		int totalCount = adminDao.findExchangeByPagination();
		pagination.setTotalCount(totalCount);
		
		List<ExchangeDto> exList = adminDao.selectExchange(pagination);
		
		returMap.put("exList", exList);
		returMap.put("pagination", pagination);
		
		return returMap;
	}

	/*
	 * 	체크박스에서 체크된 value인 exchangeNo를 배열로 받아 반복문으로 꺼내 parsing과 set후 sql작업하는 기능
	 * 	status에 value를 업데이트 될 parameter로 받아서 로직 수행
	 * 	통합 후 DTO로 parameter를 받아서 처리할 수 있도록 수정할 예정
	 */
	@Override
	public int modifyExchangeStatusByExchangeNos(List<ExchangeDto> exchangeDto) {
		log.info("Service modifyExchangeStatusByExchangeNos() start");
		log.info("exchangeDto : "+exchangeDto);
		int result = 0;
		
		for(int i = 0; i < exchangeDto.size(); i++) {
			ExchangeDto paramDto = exchangeDto.get(i);
			log.info("Exchange Service paramDto : "+paramDto.toString());
			
			if(paramDto.getExchangeStatus().equals("환전완료")) {
				result = adminDao.updateExchange(paramDto);
			} else {
				result = adminDao.updateExchange(paramDto);
			}
		}
		
		return result;
	}
	
	@Override
	public int findExchangeByPagination(Pagination pagination) {
		int totalCount = adminDao.findExchangeByPagination();
		
		return totalCount;
	}
	
	//coupon
	/*
	 * 	쿠폰 생성하기를 눌렀을때, UUID객체로 랜덤의 uuid를 생성하고, DB에 쿠폰 번호 데이터 사이즈와 타입에 맞게
	 * 	toString, substring작업을 한 뒤에 PK검증을 위해 조회하고, 결과에 따라 해당화면 입력부에 생성이 되거나 중복 alert띄우는 기능
	 * 	통합 후 데이터 전송 방식과 로직을 조금 손 볼 예정
 	 */

	

	@Override
	public Map<String, Object> findCouponListByPagination(Pagination pagination) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		int totalCount = adminDao.selectCoupon();
		pagination.setTotalCount(totalCount);
		log.info("pagination : "+pagination);
		
		List<CouponDto> couponList = adminDao.selectCouponsExpired(pagination);
		
		returnMap.put("couponList", couponList);
		returnMap.put("pagination", pagination);
		
		return returnMap;
	}

	
	@Override
	public String generateCouponNo(){
		log.info("Service generateCouponNo() start");
		List<CouponDto> couponList = null;
		String couponNo = null;
		UUID uuid = null;
		boolean result = false;
		
		do {
			uuid = UUID.randomUUID();
			couponNo = uuid.toString().substring(0, 6);
			log.info("couponNo : "+couponNo);
			couponList = adminDao.findCouponList();
			log.info("couponList : "+couponList);
			
			for(CouponDto cn : couponList) {
				if(cn.getCouponCode().equals(couponNo)) {
					result = false;
				} else {
					result = true;
				}
			}
		} while(!result);
		
		return couponNo;
	}
	
	/*
	 * 	위에 로직을 통해 생성된 쿠폰 번호를 insert하는 기능
	 * 	사용기한에 대한 논의가 부족해 통합 후 논의를 통해 사용기한 정하는 로직 구현 예정
	 */
	@Override
	public Map<String, Integer> addCoupon(CouponDto couponDto) {
		log.info("Service addCoupon() start");
		log.info("Service couponDto : " + couponDto.toString());
		
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		int valid = 0;
		
		if(couponDto.getCouponValue() == null) {
			valid = -1;
			
		} else {
			valid = adminDao.insertCoupon(couponDto);
		}
		
		returnMap.put("valid", valid);
		
		return returnMap;
	}
	
	//company-sales
	/*
	 * 	화면에 날짜 입력란에서 startDate와 endDate를 입력받아 그래프 api를 통해 매출을 출력하는 기능
	 * 	startDate가 1일로 설정되었을때, YYYY/MM 형태의 매출그래프 출력
	 * 	startDate와 endDate가 1이상으로 설정되었을때, YYYY/MM/DD 형태의 매출 그래프 출력
	 * 	통합 후 검증된 더미데이터로 sql검증 필요
	 */
	@Override
	public List<CompanySalesDto> findCompanySalesByStartDateAndEndDate(Timestamp startDate, Timestamp endDate, String periodicData) {
		log.info("Service findCompanySalesByYears() start");
		CompanySalesDto companySalesDto = new CompanySalesDto();
		List<CompanySalesDto> csList = null;
		
		if(periodicData.equals("Y")) {
			companySalesDto.setStartDate(startDate);
			companySalesDto.setEndDate(endDate);
			csList = adminDao.selectCompanySalesByYYYYDate(companySalesDto);
		} else if(periodicData.equals("M")) {
			companySalesDto.setStartDate(startDate);
			companySalesDto.setEndDate(endDate);
			csList = adminDao.selectCompanySalesByMMDate(companySalesDto);
		} else {
			companySalesDto.setStartDate(startDate);
			companySalesDto.setEndDate(endDate);
			csList = adminDao.selectCompanySalesByDDDate(companySalesDto);
		}
		
		List<CompanySalesDto> returnList = new ArrayList<CompanySalesDto>();
		
		for(int i = 0; i < csList.size(); i++) {
			CompanySalesDto returnDto = csList.get(i);
			
			Long bcCount = returnDto.getBcCount();
			Long exCount = returnDto.getExCount();
			String period = returnDto.getPeriod();
			
			bcCount = bcCount == null ? 0L : bcCount;
			exCount = exCount == null ? 0L : exCount;
			
			log.info("CompanySales Service getCout() : "+bcCount);
			log.info("CompanySales Service getExCount() : "+exCount);
			log.info("CompanySales Service getPeriod() : "+period);
			
			returnDto.setBcCount(bcCount);
			returnDto.setExCount(exCount);
			returnDto.setPeriod(period);
			
			returnList.add(returnDto);
			}
			
			return returnList;
		}
	
	//talent
	@Override
	public List<TalentDto> findTalent() {
		log.info("Talent Service findTalent() start");
		List<TalentDto> talentList = adminDao.selectTalent();
		
		return talentList;
	}
	

	@Override
	public Map<String, Object> findtalentByPagination(Pagination pagination) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int totalCount = adminDao.selectTalentByPagination();
		pagination.setTotalCount(totalCount);
		
		List<TalentDto> talentList = adminDao.selectTalents(pagination);
		
		returnMap.put("talentList", talentList);
		returnMap.put("pagination", pagination);
		
		return returnMap;
	}
	
	/*
	 * 	판매등록 신청건에 대한 게시상태를 업데이트 하는 기능
	 */
	@Override
	public int modifyTalentByTalentNos(List<TalentDto> talentDto) {
		log.info("Talent Service modifyTalentBySellerIds() start");
		int result = 0;
		
		for(int i = 0; i < talentDto.size(); i++) {
			TalentDto paramDto = talentDto.get(i);
			result = adminDao.updateTalentByTalentNos(paramDto);
		}
		
		return result;
	}
	
	@Override
	public TalentDto findTalentByTalentNo(Long talentNo) {
		log.info("Talent Service findTalentBySellerId() start");
		return adminDao.selectTalentByTalentNo(talentNo);
	}

	//talent-refund
	@Override
	public List<TalentRefundDto> findTalentRefund() {
		log.info("TalentRefund Service findTalentRefund() start");
		List<TalentRefundDto> refundList = adminDao.selectTalentRefund();
		
		return refundList;
	}
	

	@Override
	public Map<String, Object> findTalentRefundByPagination(Pagination pagination) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		int totalCount = adminDao.selectTalentRefundForTotalCount();
		pagination.setTotalCount(totalCount);
		
		List<TalentRefundDto> refundList = adminDao.selectTalentRefunByPagination(pagination);
		
		returnMap.put("refundList", refundList);
		returnMap.put("pagination", pagination);
		
		return returnMap;
	}


	/*
	 *  체크박스로 체크된 value(purchaseNo)에 대한 상태를 업데이트,
	 *  버튼의 value를 parameter로 받아 해당 버튼의 value로 업데이트 하는 기능
	 */
	@Override
	public int modifyTalentRefundByPurchaseNosAndStatus(List<TalentRefundDto> talentRefundDto) {
		log.info("Talent Refund Service modifyTalentRefundByPurchaseNosAndStatus() start");
		int result = 0;
		
		for(int i = 0; i < talentRefundDto.size(); i++) {
			TalentRefundDto paramDto = talentRefundDto.get(i);
			log.info("TalentRefund Service paramDto : "+paramDto.toString());
			
			if(paramDto.getRefundStatus().equals("환불완료")) {
				result = adminDao.updateTalentRefundToSuccessByPurchaseNosAndStatus(paramDto);
			} else {
				result = adminDao.updateTalentRefundToCompanionByPurchaseNosAndStatus(paramDto);
			}
		}
		return result;
	}
	
	//report
	@Override
	public List<ReportDto> findReport() {
		log.info("Report Service findReport() start");
		List<ReportDto> reportList = adminDao.selectReport();
		return reportList;
	}

	@Override
	public List<ReportDto> findReportByBlackId(String blackId) {
		log.info("Report Service findReportByBlackId() start");
		List<ReportDto> reportList = adminDao.selectReportByBlackId(blackId);
		log.info("reportList : "+reportList);
		
		return reportList;
	}

	@Override
	public int modifyReportByMemberId(String memberId) {
		log.info("Report Service modifyReportByMemberId() start");
		
		return adminDao.updateReportByMemberId(memberId);
	}

}
