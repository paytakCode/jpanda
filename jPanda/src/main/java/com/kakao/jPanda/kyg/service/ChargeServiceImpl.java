package com.kakao.jPanda.kyg.service;


import org.springframework.stereotype.Service;

import com.kakao.jPanda.kyg.dao.ChargeDao;
import com.kakao.jPanda.kyg.domain.ChargeDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChargeServiceImpl implements ChargeService {

	private final ChargeDao chargeDao;
	
	public ChargeServiceImpl(ChargeDao chargeDao) {
		this.chargeDao = chargeDao;
	}
	
	@Override
	public int insertCharge(ChargeDto chargeDto) {
		log.info("ChargeServiceImpl insertCharge() Start...");
		int resultCharge = chargeDao.insertCharge(chargeDto);
		log.info("ChargeServiceImpl insertCharge() DAO에서 반환받은 resultCharge-> " + resultCharge);
		return resultCharge;
		
	}

//	시큐리티 에러
	/*
	@Override
	public boolean couponCheck(String couponNo) {
		log.info("ChargeServiceImpl couponCheck() Start...");
		boolean isAvailableCoupon = chargeDao.couponCheck(couponNo);
		log.info("ChargeServiceImpl insertCharge() DAO에서 반환받은 resultCharge-> " + isAvailableCoupon);
		
		return isAvailableCoupon;
	}
	*/
	
	
	
	
	
	
	
	/*
	private final ChargeDao chargeDao;
	
	@Autowired
	public ChargeServiceImpl(ChargeDao chargeDao) {
		this.chargeDao = chargeDao;
	}
	
	public int charge() {
		System.out.println("charge");
		return charge();
	}

	
	@Override
	public void insertCharge(ChargeDto bambooChargeDto) {
		chargeDao.insertCharge(bambooChargeDto);
		
	}

	*/
	
	
	
	/*
	쿠폰 적용가능 체크 로직
	@Override
	public boolean couponCheck(String couponNo) {
		List<String> list = chargeDao.selectCoupon(couponNo);
		
		if (list.size() > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	*/
	
}
