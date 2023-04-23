package com.kakao.jPanda.yjh.service;

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
			resultStr = "<script>alert('怨듭��궗�빆 �쟻�슜�씠 �셿猷� �릺�뿀�뒿�땲�떎.'); location.href='/admin';</script>";
		} else {
			resultStr = "<script>alert('怨듭��궗�빆 �쟻�슜 �삤瑜�'); history.back();</script>";
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
			resultStr = "<script>alert('�꽦怨듭쟻�쑝濡� �닔�젙 �릺�뿀�뒿�땲�떎.'); location.href='/admin/notice'; </script>";
		} else {
			resultStr = "<script>alert('�닔�젙�뿉 �떎�뙣�븯���뒿�땲�떎.'); history.back(); </script>";
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
	public List<CompanySalesDto> findCompanySalesByYears(CompanySalesDto companySalesDto) {
		log.info("Service findCompanySalesByYears() start");
		List<CompanySalesDto> csList = adminDao.selectCompanySalesByYears(companySalesDto);
		
		return csList;
	}
	
	//talent
	@Override
	public List<Talent> findTalent() {
		log.info("Service findTalent() start");
		List<Talent> talentList = adminDao.selectTalent();
		
		return talentList;
	}
}
