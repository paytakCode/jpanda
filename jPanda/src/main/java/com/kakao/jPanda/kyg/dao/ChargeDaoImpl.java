package com.kakao.jPanda.kyg.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.PaymentDto;

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
		log.info("ChargeDaoImpl insertCouponUse() Start...");
		log.info("ChargeDaoImpl insertCouponUse chargeDto.toString -> " + chargeDto.toString());
		int resultInsertCouponUse = 0; 
		
		if(chargeDto.getCouponCode() != null) {
			try {
				resultInsertCouponUse = sqlSession.insert("insertCouponUse", chargeDto);
				log.info("ChargeDaoImpl insertCouponUse resultinsertCouponUse -> {}", resultInsertCouponUse);
			} catch (Exception e) {
				log.error("ChargeDaoImpl insertCouponUse() Exception ->  {}", e.getMessage(), e);
			}
			if(resultInsertCouponUse > 0) {
				resultInsertCouponUse = 1;
				log.info("ChargeDaoImpl resultinsertCouponUse가 삽입되었습니다");
			} else {
				resultInsertCouponUse = 0;
				log.error("ChargeDaoImpl resultinsertCouponUse가 삽입되지 않았습니다");
			}
		} else {
			log.info("ChargeDaoImpl insertCouponUse() chargeDto.getCouponCode()가 null입니다. 0을 반환합니다.");
			return resultInsertCouponUse;
		}
		
		return resultInsertCouponUse;
	}
	
	
	// 밤부 충전시 bamboo_charge Insert
	@Override
	public int insertCharge(ChargeDto chargeDto) {
		log.info("ChargeDaoImpl insertCharge() Start...");
		log.info("ChargeDaoImpl insertCharge chargeDto.toString -> {}", chargeDto.toString());
		
		int resultCharge = 0;
		try {
			resultCharge = sqlSession.insert("insertCharge", chargeDto);
			log.info("ChargeDaoImpl insertCharge resultCharge -> {}", resultCharge);
		} catch (Exception e) {
			log.error("ChargeDaoImpl insertCharge() Exception -> {}", e.getMessage(), e);
		}
		
		if(resultCharge > 0) {
			log.info("ChargeDaoImpl insertCharge() resultCharge 완료 ");
		} else {
			log.info("ChargeDaoImpl insertCharge() resultCharge 실패 ");
		}
		
		return resultCharge;
	}
	

	// coupon_use isUsed 쿠폰 검증
	@Override
	public CouponUseDto selectCouponUse(CouponUseDto couponUseDto) {
		log.info("ChargeDaoImpl selectCouponUse() Start...");
		log.info("ChargeDaoImpl selectCouponUse() chargeDto.toString -> {}", couponUseDto.toString());
		
		CouponUseDto selectCouponUseResult = null;
		try {
			selectCouponUseResult = sqlSession.selectOne("selectCouponUse", couponUseDto);
			log.info("ChargeDaoImpl ChargeDaoImpl selectCouponUseResult -> " + selectCouponUseResult);
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectCouponUse() Exception -> " + e.getMessage(), e);
		}
		
		return selectCouponUseResult;
	}

	// coupon isPeriod 쿠폰 검증
	@Override
	public CouponDto selectCouponByCouponCode(String couponCode) {
		log.info("ChargeDaoImpl selectCouponByCouponCode() Start...");
		log.info("ChargeDaoImpl selectCouponUse() chargeDto.toString -> {}", couponCode.toString());
		
		CouponDto selectCouponByCouponCodeResult = null;
		try {
			selectCouponByCouponCodeResult = sqlSession.selectOne("selectCouponByCouponCode", couponCode);
			log.info("ChargeDaoImpl ChargeDaoImpl selectCouponByCouponCodeResult -> {}", selectCouponByCouponCodeResult);
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectCouponByCouponCode() Exception -> {}", e.getMessage(), e);
		}
		
		return selectCouponByCouponCodeResult;
	}

	// 사용 가능한 쿠폰의 금액을 검증
	@Override
	public Long selectAvailAmountCoupon(CouponUseDto couponUseDto) {
		log.info("ChargeDaoImpl selectAvailAmountCoupon() Start...");
		log.info("ChargeDaoImpl selectAvailAmountCoupon() chargeDto.toString -> {}", couponUseDto.toString());
		
		Long selectAvailAmountCouponResult = (long) 0;
		try {
			selectAvailAmountCouponResult = sqlSession.selectOne("selectAvailAmountCoupon", couponUseDto);
			log.info(" ChargeDaoImpl selectAvailAmountCoupon -> " + selectAvailAmountCouponResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectAvailAmountCoupon() Exception -> " + e.getMessage(), e);
		}
		
		return selectAvailAmountCouponResult;
	}

	// findTotalBamboo 계산 DAO
	@Override
	public Long selectChargeBambooAmountByMemberId(String memberId) {
		log.info("ChargeDaoImpl selectChargeBambooAmountByMemberId() Start...");
		log.info("ChargeDaoImpl selectChargeBambooAmountByMemberId() memberId -> {}", memberId);
		
		Long selectChargeBambooAmountByMemberIdResult = (long) 0;
		
		try {
			selectChargeBambooAmountByMemberIdResult = sqlSession.selectOne("selectChargeBambooAmountByMemberId", memberId);
			log.info(" ChargeDaoImpl selectChargeBambooAmountByMemberId -> " + selectChargeBambooAmountByMemberIdResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectChargeBambooAmountByMemberId() Exception -> " + e.getMessage(), e);
		}
		
		return selectChargeBambooAmountByMemberIdResult;
	}

	@Override
	public Long selectBambooUseAmountByMemberId(String memberId) {
		log.info("ChargeDaoImpl selectBambooUseAmount() Start...");
		log.info("ChargeDaoImpl selectBambooUseAmount() memberId -> {}", memberId);
		
		Long selectBambooUseAmountByMemberIdResult = (long) 0;
		
		try {
			selectBambooUseAmountByMemberIdResult = sqlSession.selectOne("selectBambooUseAmountByMemberId", memberId);
			log.info(" ChargeDaoImpl selectBambooUseAmountByMemberId -> " + selectBambooUseAmountByMemberIdResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectBambooUseAmountByMemberId() Exception -> " + e.getMessage(), e);
		}
		
		return selectBambooUseAmountByMemberIdResult;
	}

	@Override
	public Long selectTalentRefundAmountByMemberId(String memberId) {
		log.info("ChargeDaoImpl selectTalentRefundAmountByMemberId() Start...");
		log.info("ChargeDaoImpl selectTalentRefundAmountByMemberId() memberId -> {}", memberId);
		
		Long selectTalentRefundAmountByMemberIdResult = (long) 0;
		
		try {
			selectTalentRefundAmountByMemberIdResult = sqlSession.selectOne("selectTalentRefundAmountByMemberId", memberId);
			log.info("ChargeDaoImpl selectTalentRefundAmountByMemberId -> {}", selectTalentRefundAmountByMemberIdResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectTalentRefundAmountByMemberId() Exception -> {}", e.getMessage(), e);
		}
		
		return selectTalentRefundAmountByMemberIdResult;
	}

	@Override
	public List<PaymentDto> selectPaymentList() {
		log.info("ChargeDaoImpl selectPaymentList() Start...");
		
		List<PaymentDto> selectPaymentListResult = null;
		log.info("ChargeDaoImpl selectPaymentList Start...");
		try {
			selectPaymentListResult = sqlSession.selectList("selectPaymentList");	 
			log.info("ChargeDaoImpl selectPaymentList() selectPaymentListResult.size() -> {}", selectPaymentListResult.size());
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectPaymentList() Exception -> {}", e.getMessage(), e);
		}
		
		return selectPaymentListResult;
	}

	@Override
	public List<ChargeDto> selectBambooChargeList(ChargeDto chargeDto) {
		log.info("ChargeDaoImpl selectBambooChargeListbyChargerId() Start...");
		log.info("ChargeDaoImpl selectBambooChargeListbyChargerId() chargeDto.toString() -> {}", chargeDto.toString());
		List<ChargeDto> selectBambooChargeListResult = null;
		log.info("ChargeDaoImpl selectBambooChargeList Start...");
		try {
			selectBambooChargeListResult = sqlSession.selectList("selectBambooChargeList", chargeDto);
			log.info("ChargeDaoImpl selectBambooChargeList() selectBambooChargeListResult.size() -> {}", selectBambooChargeListResult.size());
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectBambooChargeList() Exception -> {}", e.getMessage(), e);
		}
		
		return selectBambooChargeListResult;
	}

	@Override
	public int totalChargeCnt(String chargerId) {
		log.info("ChargeDaoImpl totalChargeCnt() Start...");
		int totalChargeCnt = 0;
		
		try {
			totalChargeCnt = sqlSession.selectOne("totalChargeCnt", chargerId);
			log.info("ChargeDaoImpl totalChargeCnt() count -> {}",  totalChargeCnt);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalChargeCnt() Exception -> " + e.getMessage());
		}
		
		return totalChargeCnt;
	}


}
