package com.kakao.jPanda.kyg.service;


import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.kyg.dao.ChargeDao;
import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChargeServiceImpl implements ChargeService {

	private final ChargeDao chargeDao;
	
	public ChargeServiceImpl(ChargeDao chargeDao) {
		this.chargeDao = chargeDao;
	}
	
	 //밤부 충전
	 @Override
		public int addCharge(ChargeDto chargeDto) {
			log.info("ChargeServiceImpl insertCharge() Start...");
			log.info("ChargeServiceImpl insertCharge() chargeDto.toString() : " + chargeDto.toString());
			
			//Long chargeMoney = chargeDto.getChargeMoney();
			
			if (chargeDto.getCouponNo() == "0") {
				chargeDto.setCouponNo(null);
			}
			
			//coupon_use insert
			int insertCouponUse = chargeDao.insertCouponUse(chargeDto);
			log.info("insertCouponUse 결과값 : {}", insertCouponUse);
			
			//결제방법 선택시 보너스율 적용
			//double bonusRatio = chargeDao.selectBonusRatio(chargeDto);
			//Long chargeBamboo = (long) (chargeMoney * bonusRatio / 1000); 
			//Long chargeBamboo = (long) (chargeMoney * bonusRatio / 1000); 
			
			//chargeDto.setChargeBamboo(chargeBamboo);
			
			log.info("chargeDto.getCouponNo() : " + chargeDto.getCouponNo());
			int resultInsertCharge = chargeDao.insertCharge(chargeDto);
			
			if(insertCouponUse > 0) {
				log.info("ChargeServiceImpl insertCouponUse 삽입 완료");
			} else {
				log.info("ChargeServiceImpl insertCouponUse 삽입 안함(미삽입 or 오류)");
			}
			
			log.info("ChargeServiceImpl insertCharge() DAO에서 반환받은 resultInsertCharge-> {}", resultInsertCharge);
			return resultInsertCharge;
			
		}
	 
	 
	// 사용가능한 쿠폰 체크
	@Override
	public int checkAvailableCoupon(CouponUseDto couponUseDto) {
		
		// 사용된 쿠폰  -> true / 미사용 -> false
		boolean isUsed = true;	
		boolean isValid = true;
		
		// selectCouponUse가 있으면 사용 불가한 쿠폰  boolean isUsed 검증
		CouponUseDto selectedcouponUseDto = chargeDao.selectCouponUse(couponUseDto);
		log.info("ChargeServiceImpl 초기 받아온 checkAvailableCoupon selectedcouponUseDto->" + selectedcouponUseDto);
		
		int returnResult = 1;
		
		//해당 DTO(id, couponId)가 Coupon_Use 테이블에 존재하면 사용 불가(0) 존재하지 않으면 사용 가능(1)
		if(selectedcouponUseDto == null) {
			isUsed = false;
		} 
		
		// false는 couponUse에 없는 쿠폰
		if(isUsed == false) {
			
			//해당 쿠폰이 기한남아있는지 확인 boolean isValid 검증
			CouponDto selectedCouponDto = chargeDao.selectCouponByCouponNo(couponUseDto.getCouponNo());
			log.info("CouponDto에 할당된 ChargeServiceImpl checkAvailableCoupon selectedCouponDto -> "+selectedCouponDto);
			
//			LocalDate expireDate = selectedCouponDto.getExpireDate();
//			LocalDate today = LocalDate.now();
			Timestamp issueDate = selectedCouponDto.getIssueDate();
			//Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			long currentTime = System.currentTimeMillis();
			Timestamp expireDate = selectedCouponDto.getExpireDate();
			log.info("issueDate -> " + issueDate);
			log.info("today -> " + currentTime);
			log.info("expireDate -> " + expireDate);
			
//			if(today.isBefore(expireDate)) {
//				isValid = false;
//			} else if(today.isEqual(expireDate)) {
//				isValid = false;
//			} else {
//				isValid = true;
//			}
			
			if(currentTime < expireDate.getTime() && currentTime >= issueDate.getTime()) {
			    isValid = false;
			} else {
			    isValid = true;
			}
			
			log.info("checkAvailableCoupon isUsed->"+isUsed);
			log.info("checkAvailableCoupon isValid->"+isValid);

		}
		 
		// isUsed -> false, isValid -> false인 경우 , 쿠폰 사용이 가능한 상태 1을 반환
		if  (!isUsed && !isValid) {
			returnResult = 1;
		}   else {
			returnResult = 0;
		}
		
		log.info("checkAvailableCoupon returnResult->"+returnResult);
		return returnResult;
	}

	@Override
	public Long getAvailAmountCoupon(CouponUseDto couponUseDto) {
		log.info("ChargeServiceImpl getAvailAmountCoupon() Start...");
		log.info("ChargeServiceImpl getAvailAmountCoupon() chargeDto.toString() : {}", couponUseDto.toString());
		
		Long findAvailAmountCoupon = chargeDao.selectAvailAmountCoupon(couponUseDto);
		log.info("ChargeServiceImpl getAvailAmountCoupon() findAvailAmountCoupon -> " + findAvailAmountCoupon);
		
		return findAvailAmountCoupon;
	}
	
	@Override
	public Long findTotalBamboo(String memberId) {
		
		Long selectedBambooChargeAmount = chargeDao.selectChargeBambooAmount(memberId);
		Long selectedBambooUseAmount    = chargeDao.selectBambooUseAmount(memberId);
		Long selectedTalentRefundAmount = chargeDao.selectTalentRefundAmount(memberId);
		log.info("ChargeServiceImpl calculateTotalBamboo findBambooChargeAmount -> {}", selectedBambooChargeAmount);
		log.info("ChargeServiceImpl calculateTotalBamboo findBambooUseAmount    -> {}", selectedBambooUseAmount);
		log.info("ChargeServiceImpl calculateTotalBamboo findTalentRefundAmount -> {}", selectedTalentRefundAmount);
		
		Long foundTotalBamboo = selectedBambooChargeAmount + selectedTalentRefundAmount - selectedBambooUseAmount;
		log.info("ChargeServiceImpl calculateTotalBamboo calculatedTotalBamboo  -> {}", foundTotalBamboo);
		
		return foundTotalBamboo;
	}


}
