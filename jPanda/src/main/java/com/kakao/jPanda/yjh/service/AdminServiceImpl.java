package com.kakao.jPanda.yjh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.AdminDao;
import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.Coupon;
import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.domain.Talent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final AdminDao adminDao;
	
	//notice
	@Override
	public String addNotice(Notice notice) {
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
	public List<Notice> findNotice() {
		log.info("Service findNotice() start");
		List<Notice> noticeList = adminDao.selectNotice();
		
		return noticeList;
	}

	@Override
	public Notice findNoticeByNoticeNo(String noticeNo) {
		log.info("Service findNoticeByNoticeNo() start");
		Notice notice = adminDao.selectNoticeByNoticeNo(Long.parseLong(noticeNo));
		
		return notice;
	}

	@Override
	public String modifyNotice(Notice notice) {
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
	public List<Exchange> findExchange() {
		log.info("Service findExchange() start");
		List<Exchange> exList = adminDao.selectExchange();
		
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
		Exchange exchange = null;
		
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
		List<Coupon> couponList = null;
		String couponNo = null;
		UUID uuid = null;
		boolean result = false;
		
		do {
			uuid = UUID.randomUUID();
			couponNo = uuid.toString().substring(0, 6);
			log.info("couponNo : "+couponNo);
			couponList = adminDao.findCouponList();
			log.info("couponList : "+couponList);
			
			for(Coupon cn : couponList) {
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
		Coupon coupon = new Coupon();
		coupon.setCouponNo(couponNo);
		coupon.setCouponValue(longCouponValue);
		log.info("coupon : "+coupon);
		
		adminDao.insertCoupon(coupon);
		
	}
	
	//company-sales
	@Override
	public List<CompanySalesDto> findCompanySalesAtBambooChargeByStartDateAndEndDate(String startDate, String endDate) {
		log.info("Service findCompanySalesByYears() start");
		CompanySalesDto companySalesDto = new CompanySalesDto();
		List<CompanySalesDto> csList = null;
		
		if(Integer.parseInt(startDate.substring(6)) > 1 && Integer.parseInt(endDate.substring(6)) > 1) {
			companySalesDto.setStartDate(startDate);
			log.info("Company-sales Service 'DD' startDate : "+companySalesDto.getStartDate().toString());
			companySalesDto.setEndDate(endDate);
			log.info("Company-sales Service 'DD' endDate : "+companySalesDto.getEndDate().toString());
			csList = adminDao.selectCompanySalesAtBambooChargeByDDDate(companySalesDto);
			
		} else {
			companySalesDto.setStartDate(startDate);
			companySalesDto.setEndDate(endDate);
			csList = adminDao.selectCompanySalesAtBambooChargeByYYMMDate(companySalesDto);
			log.info("Company-sales Service YYYYMM csList : "+csList.toString());
		}
		
		CompanySalesDto companySalesDtoForExchange = new CompanySalesDto();
		List<CompanySalesDto> csExList = null;
		
		if(Integer.parseInt(startDate.substring(6)) > 1 && Integer.parseInt(endDate.substring(6)) > 1) {
			companySalesDtoForExchange.setStartDate(startDate);
			log.info("Company-sales Service 'DD' startDate : "+companySalesDtoForExchange.getStartDate().toString());
			companySalesDtoForExchange.setEndDate(endDate);
			log.info("Company-sales Service 'DD' endDate : "+companySalesDtoForExchange.getEndDate().toString());
			csExList = adminDao.selectCompanySalesAtExchangeByDDDate(companySalesDtoForExchange);
			
		} else {
			companySalesDtoForExchange.setStartDate(startDate);
			companySalesDtoForExchange.setEndDate(endDate);
			csExList = adminDao.selectCompanySalesAtExchangeByYYMMDate(companySalesDtoForExchange);
			log.info("Company-sales Service YYYYMM csExList : "+csExList.toString());
		}
		
//		csList.addAll(csExList);
//		List<CompanySalesDto> returnList = new ArrayList<CompanySalesDto>();
//		
//		for(int i = 0; i < csList.size(); i++) {
//			CompanySalesDto ex = csList.get(i);
//			
//			Long exCount = ex.getCount();
//			Long exExCount = ex.getExCount();
//			String exYearData = ex.getYearData();
//			String exExData = ex.getExData();
//			
//			if (exCount == null) {
//			    exCount = 0L;
//			}
//			
//			if (exExCount == null) {
//			    exExCount = 0L;
//			}
//			
//			if (exYearData == null && exExCount > 0) {
//			    exYearData = exExData;
//			} else if (exExData == null && exCount > 0) {
//			    exExData = exYearData;    
//			}
//			
//			log.info("exCount : "+exCount);
//			log.info("exExCount : "+exExCount);
//			log.info("exYearData : " + exYearData);
//			
//			ex.setCount(exCount);
//			ex.setExCount(exExCount);
//			ex.setYearData(exYearData);
//			ex.setExData(null);
//			
//			returnList.add(ex);
//		}
//		
//		log.info("returnList : "+returnList);
//		
//		return returnList;
	}
	
	//talent
	@Override
	public List<Talent> findTalent() {
		log.info("Service findTalent() start");
		List<Talent> talentList = adminDao.selectTalent();
		
		return talentList;
	}

}
