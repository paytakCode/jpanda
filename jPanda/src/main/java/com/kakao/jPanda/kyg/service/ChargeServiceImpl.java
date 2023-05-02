package com.kakao.jPanda.kyg.service;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.kyg.dao.ChargeDao;
import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.ChargeHistoryDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.PaymentDto;

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
			
			if (chargeDto.getCouponCode() == "0") {
				chargeDto.setCouponCode(null);
			}
			
			//coupon_use insert
			int insertCouponUse = chargeDao.insertCouponUse(chargeDto);
			log.info("insertCouponUse 결과값 : {}", insertCouponUse);
			
			log.info("chargeDto.getCouponCode() : " + chargeDto.getCouponCode());
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
		
		boolean isAvailable = false;	
		boolean isValidPeriod = false;
		int returnResult = 0;
		
		// selectCouponUse가 있으면 사용 불가한 쿠폰  boolean isAvailable 검증
		CouponUseDto selectedcouponUseDto = chargeDao.selectCouponUse(couponUseDto);
		log.info("checkAvailableCoupon selectedcouponUseDto -> {}", selectedcouponUseDto);
		
		//DTO(memberId, couponCode)가 Coupon_Use 테이블에 존재하면 사용 불가 / 존재하지 않으면 사용 가능
		if(selectedcouponUseDto == null) {
			isAvailable = true;
		} 
		
		// true는 isAvailable에 없는 쿠폰
		if(isAvailable == true) {
			
			// 해당 쿠폰이 기한남아있는지 확인 boolean isAvailable 검증
			CouponDto selectedCouponDto = chargeDao.selectCouponByCouponCode(couponUseDto.getCouponCode());
			log.info("CouponDto에 할당된 ChargeServiceImpl checkAvailableCoupon selectedCouponDto -> "+selectedCouponDto);
			
			// coupon TB에 쿠폰이 없는경우 isValidPeriod = false
			if(selectedCouponDto == null) {
				isValidPeriod = false;
			} else {
				
				Timestamp issueDate = selectedCouponDto.getIssueDate();
				long currentTime = System.currentTimeMillis();		// Long
				Timestamp timestamp = new Timestamp(currentTime);
				Timestamp expireDate = selectedCouponDto.getExpireDate();
				log.info("issueDate -> " + issueDate);
				log.info("Current Time Stamp: " + timestamp);
				log.info("expireDate -> " + expireDate);
				
				
				if(currentTime <= expireDate.getTime() && currentTime >= issueDate.getTime()) {
					isValidPeriod = true;
				} else {
					isValidPeriod = false;
				}
				
				log.info("checkAvailableCoupon isAvailable -> "+isAvailable);
				log.info("checkAvailableCoupon isValidPeriod -> "+isValidPeriod);

			}
	
		}
		 
		// isAvailable -> true, isValidPeriod -> true인 경우 , 쿠폰 사용이 가능한 상태 1을 반환
		if  (isAvailable && isValidPeriod) {
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
		log.info("ChargeServiceImpl getAvailAmountCoupon() chargeDto.toString() -> {}", couponUseDto.toString());
		
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

	@Override
	public List<PaymentDto> findPaymentList(PaymentDto paymentDto) {
		
		List<PaymentDto> selectPaymentList = null;
		log.info("ChargeServiceImpl findPaymentList() Start...");
		
		selectPaymentList = chargeDao.selectPaymentList(paymentDto);
		log.info("ChargeServiceImpl findPaymentList() selectPaymentList.size() -> {}", selectPaymentList.size());
		
		return selectPaymentList;
	}

	@Override
	public List<ChargeHistoryDto> findChargeHistoryList(ChargeHistoryDto chargeHistoryListDto) {
		List<ChargeHistoryDto> selectChargeHistoryList = null;
		log.info("ChargeServiceImpl findChargeHistoryList() Start...");
		
		selectChargeHistoryList = chargeDao.selectChargeHistoryList(chargeHistoryListDto);
		log.info("ChargeServiceImpl findPaymentList() selectChargeHistoryList.size() -> {}", selectChargeHistoryList.size());
		
		return selectChargeHistoryList;
	}
	
	


}
