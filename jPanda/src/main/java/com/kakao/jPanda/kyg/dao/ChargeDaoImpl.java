package com.kakao.jPanda.kyg.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class ChargeDaoImpl implements ChargeDao {

//	mybatis와 연결
	private final SqlSession sqlSession;
	
	@Autowired
	public ChargeDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
//	밤부 충전시 coupon_use Insert
	@Override
	public int insertCouponUse(ChargeDto chargeDto) {
		log.info("ChargeDaoImpl insertCouponUse() Start...");
		System.out.println("ChargeDaoImpl insertCouponUse chargeDto.toString -> " + chargeDto.toString());
		int resultinsertCouponUse = 0; 
		try {
			resultinsertCouponUse = sqlSession.insert("insertCouponUse", chargeDto);
			System.out.println("ChargeDaoImpl insertCouponUse resultinsertCouponUse -> " + resultinsertCouponUse);
		} catch (Exception e) {
			System.out.println("ChargeDaoImpl insertCouponUse() Exception -> " + e.getMessage());
		}
		if(resultinsertCouponUse > 0) {
			System.out.println("ChargeDaoImpl resultinsertCouponUse가 삽입되었습니다");
		} else {
			System.out.println("ChargeDaoImpl resultinsertCouponUse가 삽입되지 않았습니다");
		}
		
		return resultinsertCouponUse;
	}
	
//	밤부 충전시 bamboo_charge Insert
	@Override
	public int insertCharge(ChargeDto chargeDto) {
		log.info("ChargeDaoImpl insertCharge() Start...");
		
		System.out.println("ChargeDaoImpl insertCharge chargeDto.toString -> " + chargeDto.toString());
		
		int resultCharge = 0;
		int couponUseInsert = 0;
		try {
			resultCharge = sqlSession.insert("insertCharge", chargeDto);
			System.out.println("ChargeDaoImpl insertCouponUse resultCharge -> " + resultCharge);
//			couponUseInsert = sqlSession.insert("insertCouponUse", chargeDto);
		} catch (Exception e) {
			System.out.println("ChargeDaoImpl insertCharge() Exception -> " + e.getMessage());
		}
		
		if(resultCharge > 0) {
			System.out.println("ChargeDaoImpl insertCharge() resultCharge 완료 ");
		} else {
			System.out.println("ChargeDaoImpl insertCharge() resultCharge 실패 ");
		}
		
		return resultCharge;
	}
	
//	밤부 충전시 payment select 
	@Override
	public double selectBonusRatio(ChargeDto chargeDto) {
		
		log.info("ChargeDaoImpl selectBonusRatio() Start...");
		System.out.println("ChargeDaoImpl selectBonusRatio chargeDto.toString -> " + chargeDto.toString());
		
		double selectBonusRatioResult = (double) 0;
		try {
			selectBonusRatioResult = sqlSession.selectOne("selectBonusRatio", chargeDto);
			System.out.println("ChargeDaoImpl ChargeDaoImpl selectBonusRatioResult -> " + selectBonusRatioResult);
		} catch (Exception e) {
//			log.error("ChargeDaoImpl selectBonusRatio() Exception -> {}", e.getMessage(), e);
			System.out.println("ChargeDaoImpl selectBonusRatio() Exception -> " + e.getMessage());
		}
		
		return selectBonusRatioResult;
	}

//	coupon_use isUsed 쿠폰 검증
	@Override
	public CouponUseDto selectCouponUse(CouponUseDto couponUseDto) {
		log.info("ChargeDaoImpl selectCouponUse() Start...");
		
		CouponUseDto selectCouponUseResult = null;
		try {
			selectCouponUseResult = sqlSession.selectOne("selectCouponUse", couponUseDto);
			System.out.println("ChargeDaoImpl ChargeDaoImpl selectCouponUseResult -> " + selectCouponUseResult);
		} catch (Exception e) {
			System.out.println("ChargeDaoImpl selectCouponUse() Exception -> " + e.getMessage());
		}
		
		return selectCouponUseResult;
	}

//	coupon isExpired 쿠폰 검증
	@Override
	public CouponDto selectCouponByCouponNo(String couponNo) {
		log.info("ChargeDaoImpl selectCouponByCouponNo() Start...");
		
		CouponDto selectCouponByCouponNoResult = null;
		try {
			selectCouponByCouponNoResult = sqlSession.selectOne("selectCouponByCouponNo", couponNo);
			System.out.println("ChargeDaoImpl ChargeDaoImpl selectCouponByCouponNoResult -> " + selectCouponByCouponNoResult);
		} catch (Exception e) {
			System.out.println("ChargeDaoImpl selectCouponByCouponNo() Exception -> " + e.getMessage());
		}
		
		return selectCouponByCouponNoResult;
	}

	



}
