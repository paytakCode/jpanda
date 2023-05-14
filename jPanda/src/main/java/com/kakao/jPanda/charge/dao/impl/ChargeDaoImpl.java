package com.kakao.jPanda.charge.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.charge.dao.ChargeDao;
import com.kakao.jPanda.charge.domain.ChargeDto;
import com.kakao.jPanda.charge.domain.CouponDto;
import com.kakao.jPanda.charge.domain.CouponUseDto;
import com.kakao.jPanda.charge.domain.PaginationDto;
import com.kakao.jPanda.charge.domain.PaymentDto;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class ChargeDaoImpl implements ChargeDao {

	private final SqlSession sqlSession;
	
	@Autowired
	public ChargeDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 밤부 충전시 coupon_use Insert
	@Override
	public int insertCouponUse(ChargeDto chargeDto) {
		log.info("[insertCouponUse] Start...");
		log.info("[insertCouponUse] chargeDto.toString -> " + chargeDto.toString());
		int resultInsertCouponUse = 0; 
		
		if(chargeDto.getCouponCode() != null) {
			try {
				resultInsertCouponUse = sqlSession.insert("insertCouponUse", chargeDto);
				log.info("[insertCouponUse] resultinsertCouponUse -> {}", resultInsertCouponUse);
			} catch (Exception e) {
				log.error("[insertCouponUse] Exception -> {}", e.getMessage(), e);
			}
			if(resultInsertCouponUse > 0) {
				resultInsertCouponUse = 1;
				log.info("[insertCouponUse] resultinsertCouponUse가 삽입되었습니다");
			} else {
				resultInsertCouponUse = 0;
				log.error("[insertCouponUse] resultinsertCouponUse가 삽입되지 않았습니다");
			}
		} else {
			log.info("[insertCouponUse] chargeDto.getCouponCode()가 null입니다. 0을 반환합니다.");
			return resultInsertCouponUse;
		}
		
		return resultInsertCouponUse;
	}
	
	
	// 밤부 충전시 bamboo_charge Insert
	@Override
	public int insertCharge(ChargeDto chargeDto) {
		log.info("[insertCharge] Start...");
		log.info("[insertCharge] chargeDto.toString -> {}", chargeDto.toString());
		
		int resultCharge = 0;
		try {
			resultCharge = sqlSession.insert("insertCharge", chargeDto);
			log.info("[insertCharge] resultCharge -> {}", resultCharge);
		} catch (Exception e) {
			log.error("[insertCharge] Exception -> {}", e.getMessage(), e);
		}
		
		if(resultCharge > 0) {
			log.info("[insertCharge] resultCharge 완료 ");
		} else {
			log.info("[insertCharge] resultCharge 실패 ");
		}
		
		return resultCharge;
	}
	

	// coupon_use isUsed 쿠폰 검증
	@Override
	public CouponUseDto selectCouponUse(CouponUseDto couponUseDto) {
		log.info("[selectCouponUse] Start...");
		log.info("[selectCouponUse] chargeDto.toString -> {}", couponUseDto.toString());
		log.info("[selectCouponUse] couponUseDto.getCouponCode().toString(); -> {}", couponUseDto.getCouponCode().toString());
		
		CouponUseDto selectCouponUseResult = null;
		try {
			selectCouponUseResult = sqlSession.selectOne("selectCouponUse", couponUseDto);
			log.info("[selectCouponUse] selectCouponUseResult -> {}", selectCouponUseResult);
		} catch (Exception e) {
			log.error("[selectCouponUse] selectCouponUseResult() Exception -> {}, {}", e.getMessage(), e);
			return null;
		}
		
		return selectCouponUseResult;
	}

	// coupon isPeriod 쿠폰 검증
	@Override
	public CouponDto selectCouponByCouponCode(String couponCode) {
		log.info("[selectCouponByCouponCode] Start...");
		log.info("[selectCouponByCouponCode] chargeDto.toString -> {}", couponCode.toString());
		
		CouponDto selectCouponByCouponCodeResult = null;
		try {
			selectCouponByCouponCodeResult = sqlSession.selectOne("selectCouponByCouponCode", couponCode);
			log.info("[selectCouponByCouponCode] selectCouponByCouponCodeResult -> {}", selectCouponByCouponCodeResult);
		} catch (Exception e) {
			log.error("[selectCouponByCouponCode] Exception -> {}, {}", e.getMessage(), e);
		}
		
		return selectCouponByCouponCodeResult;
	}

	// 사용 가능한 쿠폰의 금액을 검증
	@Override
	public Long selectAvailAmountCoupon(CouponUseDto couponUseDto) {
		log.info("[selectAvailAmountCoupon] Start...");
		log.info("[selectAvailAmountCoupon] chargeDto.toString -> {}", couponUseDto.toString());
		
		Long selectAvailAmountCouponResult = (long) 0;
		try {
			selectAvailAmountCouponResult = sqlSession.selectOne("selectAvailAmountCoupon", couponUseDto);
			log.info("[selectAvailAmountCoupon] selectAvailAmountCouponResult -> " + selectAvailAmountCouponResult);		
		} catch (Exception e) {
			log.error("[selectAvailAmountCoupon] selectAvailAmountCouponResult Exception -> " + e.getMessage(), e);
		}
		
		return selectAvailAmountCouponResult;
	}

	// findTotalBamboo 계산 DAO
	@Override
	public Long selectChargeBambooAmountByMemberId(String memberId) {
		log.info("[selectChargeBambooAmountByMemberId] Start...");
		log.info("[selectChargeBambooAmountByMemberId] memberId -> {}", memberId);
		
		Long selectChargeBambooAmountByMemberIdResult = (long) 0;
		
		try {
			selectChargeBambooAmountByMemberIdResult = sqlSession.selectOne("selectChargeBambooAmountByMemberId", memberId);
			log.info("[selectChargeBambooAmountByMemberId] selectChargeBambooAmountByMemberIdResult -> " + selectChargeBambooAmountByMemberIdResult);		
		} catch (Exception e) {
			log.error("[selectChargeBambooAmountByMemberId] selectChargeBambooAmountByMemberIdResult Exception -> " + e.getMessage(), e);
		}
		
		return selectChargeBambooAmountByMemberIdResult;
	}

	@Override
	public Long selectBambooUseAmountByMemberId(String memberId) {
		log.info("[selectBambooUseAmountByMemberId] Start...");
		log.info("[selectBambooUseAmountByMemberId] memberId -> {}", memberId);
		
		Long selectBambooUseAmountByMemberIdResult = (long) 0;
		
		try {
			selectBambooUseAmountByMemberIdResult = sqlSession.selectOne("selectBambooUseAmountByMemberId", memberId);
			log.info("[selectBambooUseAmountByMemberId] selectBambooUseAmountByMemberIdResult -> " + selectBambooUseAmountByMemberIdResult);		
		} catch (Exception e) {
			log.error("[selectBambooUseAmountByMemberId] selectBambooUseAmountByMemberIdResult Exception -> " + e.getMessage(), e);
		}
		
		return selectBambooUseAmountByMemberIdResult;
	}

	@Override
	public Long selectTalentRefundAmountByMemberId(String memberId) {
		log.info("[selectTalentRefundAmountByMemberId] Start...");
		log.info("[selectTalentRefundAmountByMemberId] memberId -> {}", memberId);
		
		Long selectTalentRefundAmountByMemberIdResult = (long) 0;
		
		try {
			selectTalentRefundAmountByMemberIdResult = sqlSession.selectOne("selectTalentRefundAmountByMemberId", memberId);
			log.info("[selectTalentRefundAmountByMemberId] selectTalentRefundAmountByMemberIdResult -> {}", selectTalentRefundAmountByMemberIdResult);		
		} catch (Exception e) {
			log.error("[selectTalentRefundAmountByMemberId] selectTalentRefundAmountByMemberIdResult Exception -> {}", e.getMessage(), e);
		}
		
		return selectTalentRefundAmountByMemberIdResult;
	}

	@Override
	public List<PaymentDto> selectPaymentList() {
		log.info("[selectPaymentList] Start...");
		
		List<PaymentDto> selectPaymentListResult = null;
		try {
			selectPaymentListResult = sqlSession.selectList("selectPaymentList");	 
			log.info("[selectPaymentList] selectPaymentListResult.size() -> {}", selectPaymentListResult.size());
		} catch (Exception e) {
			log.error("[selectPaymentList] selectPaymentListResult Exception -> {}", e.getMessage(), e);
		}
		
		return selectPaymentListResult;
	}

	@Override
	public int totalChargeCntByChargerId(String chargerId) {
		log.info("[totalChargeCntByChargerId] Start...");
		int totalChargeCntByChargerId = 0;
		
		try {
			totalChargeCntByChargerId = sqlSession.selectOne("totalChargeCntByChargerId", chargerId);
			log.info("[totalChargeCntByChargerId] totalChargeCntByChargerId count -> {}",  totalChargeCntByChargerId);
		} catch (Exception e) {
			log.debug("[totalChargeCntByChargerId] totalChargeCntByChargerId Exception -> {}", e.getMessage());
		}
		
		return totalChargeCntByChargerId;
	}

	@Override
	public List<ChargeDto> selectChargeListByPagination(PaginationDto paginationDto) {
		log.info("[selectChargeListByPagination] start");
		log.info("[selectChargeListByPagination] pagination.toString -> " + paginationDto.toString());
		List<ChargeDto> chargeListByPagination = null;
		
		try {
			chargeListByPagination = sqlSession.selectList("selectChargeListByPagination", paginationDto);
			log.info("[selectChargeListByPagination] chargeListByPagination.toString -> " + chargeListByPagination.toString());
		} catch(Exception e) {
			log.debug("[selectChargeListByPagination] chargeListByPagination Exception -> {}", e.getMessage());
			e.printStackTrace();
		}
		
		return chargeListByPagination;
	}

}
