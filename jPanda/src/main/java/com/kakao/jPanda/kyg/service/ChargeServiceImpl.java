package com.kakao.jPanda.kyg.service;


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
	
//	  밤부 충전
	 @Override
		public int addCharge(ChargeDto chargeDto) {
			log.info("ChargeServiceImpl insertCharge() Start...");
			
			Long chargeMoney = chargeDto.getChargeMoney();
			String paymentMethod = chargeDto.getPaymentMethod();

//			coupon_use insert
			int insertCouponUse = chargeDao.insertCouponUse(chargeDto);
			
//			결제방법 선택시 보너스율 적용
			double bonusRatio = chargeDao.selectBonusRatio(chargeDto);
			Long chargeBamboo = (long) (chargeMoney * bonusRatio / 1000);
			chargeDto.setChargeBamboo(chargeBamboo);
			
			int resultInsertCharge = chargeDao.insertCharge(chargeDto);
			
			if(insertCouponUse > 0 && insertCouponUse > 0) {
				System.out.println("insertCouponUse 삽입 완료");
			} else {
				System.out.println("insertCouponUse 삽입 안함(미삽입 or 오류)");
			}
			
			log.info("ChargeServiceImpl insertCharge() DAO에서 반환받은 resultInsertCharge-> " + resultInsertCharge);
			return resultInsertCharge;
			
		}
	 
	

	@Override
	public int checkAvailableCoupon(CouponUseDto couponUseDto) {
		// selectCouponUse가 있으면 사용 불가한 쿠폰  boolean isUsed 검증
		CouponUseDto selectedcouponUseDto = chargeDao.selectCouponUse(couponUseDto);
		
		int returnResult = 1;
		
		// 사용된 쿠폰  -> true 미사용 false
		boolean isUsed = true;	
		boolean isExpired = true;
		System.out.println("초기 받아온 checkAvailableCoupon selectedcouponUseDto->" + selectedcouponUseDto);
		//해당 DTO(id, couponId)가 Coupon_Use 테이블에 존재하면 사용 불가(0) 존재하지 않으면 사용 가능(1)
		if(selectedcouponUseDto == null) {
			isUsed = false;
		} 
		
		/* false가 couponUse에 없는 쿠폰 */
		if(isUsed == false) {
			
			//해당 쿠폰이 기한남아있는지 확인 boolean isExpired 검증
			CouponDto selectedCouponDto = chargeDao.selectCouponByCouponNo(couponUseDto.getCouponNo());
			System.out.println("CouponDto에 할당된 checkAvailableCoupon selectedCouponDto->"+selectedCouponDto);
			LocalDate expireDate = selectedCouponDto.getExpireDate();
			LocalDate today = LocalDate.now();
			if(today.isBefore(expireDate)) {
				isExpired = false;
			} else if(today.isEqual(expireDate)) {
				isExpired = false;
			} else {
				isExpired = true;
			}
			System.out.println("checkAvailableCoupon isUsed->"+isUsed);
			System.out.println("checkAvailableCoupon isExpired->"+isExpired);

		}
		 
		// isUsed -> false, isExpired -> false인 경우 , 쿠폰 사용이 가능한 상태 1을 반환
		if  (!isUsed && !isExpired) {
			returnResult = 1;
		}   else {
			returnResult = 0;
		}
		
		System.out.println("checkAvailableCoupon returnResult->"+returnResult);
		return returnResult;
	}

	@Override
	public int calculateTotalBamboo(String memberId) {
		
//		int selectBambooChargeBamboo = chargeDao.selectBambooChargeBamboo();
//		int selectBambooUseBamboo = chargeDao.selectBambooUseUseBamboo();
		
		
		
		
		return 0;
	}


}
