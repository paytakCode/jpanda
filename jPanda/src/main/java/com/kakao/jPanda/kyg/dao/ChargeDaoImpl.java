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
	
	// 밤부 충전시 payment select 
	@Override
	public double selectBonusRatio(ChargeDto chargeDto) {
		
		log.info("ChargeDaoImpl selectBonusRatio() Start...");
		log.info("ChargeDaoImpl selectBonusRatio chargeDto.toString -> {}", chargeDto.toString());
		
		double selectBonusRatioResult = (double) 0;
		try {
			selectBonusRatioResult = sqlSession.selectOne("selectBonusRatio", chargeDto);
			log.info("ChargeDaoImpl ChargeDaoImpl selectBonusRatioResult -> {}", selectBonusRatioResult);
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectBonusRatio() Exception -> {}", e.getMessage(), e);
		}
		
		return selectBonusRatioResult;
	}

	// coupon_use isUsed 쿠폰 검증
	@Override
	public CouponUseDto selectCouponUse(CouponUseDto couponUseDto) {
		log.info("ChargeDaoImpl selectCouponUse() Start...");
		
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
	public Long selectChargeBambooAmount(String memberId) {
		log.info("ChargeDaoImpl selectChargeBambooAmount() Start...");
		Long selectChargeBambooAmountResult = (long) 0;
		
		try {
			selectChargeBambooAmountResult = sqlSession.selectOne("selectChargeBambooAmount", memberId);
			log.info(" ChargeDaoImpl selectChargeBambooAmount -> " + selectChargeBambooAmountResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectChargeBambooAmount() Exception -> " + e.getMessage(), e);
		}
		
		return selectChargeBambooAmountResult;
	}

	@Override
	public Long selectBambooUseAmount(String memberId) {
		log.info("ChargeDaoImpl selectBambooUseAmount() Start...");
		Long selectBambooUseAmountResult = (long) 0;
		
		try {
			selectBambooUseAmountResult = sqlSession.selectOne("selectBambooUseAmount", memberId);
			log.info(" ChargeDaoImpl selectBambooUseAmount -> " + selectBambooUseAmountResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectBambooUseAmount() Exception -> " + e.getMessage(), e);
		}
		
		return selectBambooUseAmountResult;
	}

	@Override
	public Long selectTalentRefundAmount(String memberId) {
		log.info("ChargeDaoImpl selectTalentRefundAmount() Start...");
		Long selectTalentRefundAmountResult = (long) 0;
		
		try {
			selectTalentRefundAmountResult = sqlSession.selectOne("selectTalentRefundAmount", memberId);
			log.info("ChargeDaoImpl selectTalentRefundAmount -> {}", selectTalentRefundAmountResult);		
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectTalentRefundAmount() Exception -> {}", e.getMessage(), e);
		}
		
		return selectTalentRefundAmountResult;
	}

	@Override
	public List<PaymentDto> selectPaymentList(PaymentDto selectMethodBonusDto) {
		
		List<PaymentDto> selectPaymentListResult = null;
		log.info("ChargeDaoImpl selectPaymentList Start...");
		try {
			selectPaymentListResult = sqlSession.selectList("selectPaymentList", selectMethodBonusDto);	 
			log.info("ChargeDaoImpl selectPaymentList() selectPaymentListResult.size() -> {}", selectPaymentListResult.size());
		} catch (Exception e) {
			log.error("ChargeDaoImpl selectPaymentList() Exception -> {}", e.getMessage(), e);
		}
		
		return selectPaymentListResult;
	}

}
