package com.kakao.jPanda.yjh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.AdminDao;
import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
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
	@Override
	public String addNotice(NoticeDto notice) {
		log.info("Service addNotice() start");
		String resultStr = "";
		
		int result = adminDao.insertNotice(notice);
		
		if(result > 0) {
			resultStr = "<script>alert('공지사항 적용이 완료되었습니다.'); location.href='/admin';</script>";
		} else {
			resultStr = "<script>alert('공지사항 적용 오류'); history.back();</script>";
		}
		return resultStr;
	}

	@Override
	public List<NoticeDto> findNotice() {
		log.info("Service findNotice() start");
		List<NoticeDto> noticeList = adminDao.selectNotice();
		
		return noticeList;
	}

	@Override
	public NoticeDto findNoticeByNoticeNo(String noticeNo) {
		log.info("Service findNoticeByNoticeNo() start");
		NoticeDto notice = adminDao.selectNoticeByNoticeNo(Long.parseLong(noticeNo));
		
		return notice;
	}

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
	public List<ExchangeDto> findExchange() {
		log.info("Service findExchange() start");
		List<ExchangeDto> exList = adminDao.selectExchange();
		
		return exList;
	}

//	@Override
//	public void modifyExchangeByExchangeNos(String[] exchangeNo, String status) {
//		System.out.println("===== ExchangeService modifyExchangeByExchangeNos() start =====");
//
//		Long[] longExchangeNo = new Long[exchangeNo.length];
//		
//		for(int i = 0; i < exchangeNo.length; i++) {
//			longExchangeNo[i] = Long.parseLong(exchangeNo[i]);
//			System.out.println(longExchangeNo[i]);
//		} 
//		
//		List<Long> listExchangeNo = new ArrayList<Long>();
//		
//		for(Long a : longExchangeNo) {
//			listExchangeNo.add(a);
//			System.out.println(listExchangeNo.toString());
//		}
//		if(status.equals("�솚�쟾�셿猷�")) {
//			System.out.println("�솚�쟾�셿猷� 濡쒖쭅");
//			exchangeDao.updateExchangeByExchangeNos(listExchangeNo);
//			
//		} else if(status.equals("諛섎젮")) {
//			System.out.println("諛섎젮濡쒖쭅");
//			exchangeDao.updateExchangeByExchangeNosToCompanion(listExchangeNo);
//		}
//		
//	}
	
	@Override
	public void modifyExchangeStatusByExchangeNos(String[] exchangeNoArray, String status) {
		log.info("Service modifyExchangeStatusByExchangeNos() start");
		Long exchangeNo = null;
		ExchangeDto exchange = null;
		
		for(String exchangeNoStr : exchangeNoArray) {
			exchangeNo = Long.parseLong(exchangeNoStr);
			exchange = adminDao.selectExchangeByExchangeNo(exchangeNo);
			exchange.setStatus(status);
			log.info("exchange : "+exchange.toString());
			adminDao.updateExchange(exchange);
		}
	}
	
	//coupon
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
				if(cn.getCouponNo().equals(couponNo)) {
					result = false;
				} else {
					result = true;
				}
			}
		} while(!result);
		
		return couponNo;
	}
	
	@Override
	public void addCoupon(String couponValue, String couponNo) {
		log.info("Service addCoupon() start");
		Long longCouponValue = Long.parseLong(couponValue);
		log.info("longCouponValue : "+longCouponValue.toString());
		
//		Coupon coupon = null;
		CouponDto coupon = new CouponDto();
		coupon.setCouponNo(couponNo);
		coupon.setCouponValue(longCouponValue);
		log.info("coupon : "+coupon);
		
		adminDao.insertCoupon(coupon);
		
	}
	
	//company-sales
	@Override
	public List<CompanySalesDto> findCompanySalesByStartDateAndEndDate(String startDate, String endDate) {
		log.info("Service findCompanySalesByYears() start");
		CompanySalesDto companySalesDto = new CompanySalesDto();
		List<CompanySalesDto> csList = null;
		
		if(Integer.parseInt(startDate.substring(6)) > 1 && Integer.parseInt(endDate.substring(6)) > 1) {
			companySalesDto.setStartDate(startDate);
			log.info("Company-sales Service 'DD' startDate : "+companySalesDto.getStartDate().toString());
			companySalesDto.setEndDate(endDate);
			log.info("Company-sales Service 'DD' endDate : "+companySalesDto.getEndDate().toString());
			csList = adminDao.selectCompanyByDDDate(companySalesDto);
			
		} else {
			companySalesDto.setStartDate(startDate);
			companySalesDto.setEndDate(endDate);
			csList = adminDao.selectCompanySalesByYYMMDate(companySalesDto);
			log.info("Company-sales Service YYYYMM csList : "+csList.toString());
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
	public String modifyTalentBySellerIds(List<String> sellerId) {
		log.info("Talent Service modifyTalentBySellerIds() start");
		int returnValue = adminDao.updateTalentBySellerIds(sellerId);
		String returnStr = "";
		
		if(returnValue > 0) {
			returnStr = "<script>alert('서비스가 정상적으로 등록되었습니다'); location.href='/admin/talents';</script>";
		} else {
			returnStr = "<script>alert('서비스 등록중 오류가 발생했습니다'); history.back();</script>";
		}
		return returnStr;
	}

	//talent-refund
	@Override
	public List<TalentRefundDto> findTalentRefund() {
		log.info("TalentRefund Service findTalentRefund() start");
		List<TalentRefundDto> refundList = adminDao.selectTalentRefund();
		
		return refundList;
	}

	@Override
	public int modifyTalentRefundByPurchaseNosAndStatus(List<TalentRefundDto> talentRefundDto) {
		log.info("Talent Refund Service modifyTalentRefundByPurchaseNosAndStatus() start");
		int result = 0;
		for(TalentRefundDto comparisonWithParam : talentRefundDto) {
			if(comparisonWithParam.getStatus().equals("환불완료")) {
				
				
				result = adminDao.updateTalentRefundToSuccessByPurchaseNosAndStatus(talentRefundDto);
			} else {
				result = adminDao.updateTalentRefundToCompanionByPurchaseNosAndStatus(talentRefundDto);
			}
		}
		return result;
	}

}
