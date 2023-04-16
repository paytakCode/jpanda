package com.kakao.jPanda.kyg.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.kyg.domain.ChargeDto;

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
	

	@Override
	public int insertCharge(ChargeDto chargeDto) {
		log.info("ChargeDaoImpl insertCharge() Start...");
		
		System.out.println("chargeDto.toString -> " + chargeDto.toString());
		
		int resultCharge = 0;
		try {
			resultCharge = sqlSession.insert("chargeInsert", chargeDto);
		} catch (Exception e) {
			System.out.println("ChargeDaoImpl insertCharge() Exception -> " + e.getMessage());
		}
		
		return resultCharge;
		
	}


//	시큐리티 에러
	
	@Override
	public int checkCoupon(String couponNo) {
		
		log.info("ChargeDaoImpl getcouponCheck() Start...");
		int checkCouponResult = 0;
		System.out.println("couponNo.toString -> " + couponNo);
		try {
			checkCouponResult = sqlSession.selectOne("couponCheck", couponNo);
			System.out.println("ChargeDaoImpl ChargeDaoImpl couponCheckResult ->" + checkCouponResult);
		} catch (Exception e) {
			log.error("ChargeDaoImpl getcouponCheck() Exception -> {}", e.getMessage(), e);
//			throw new RuntimeException("Failed to check coupon", e);
			System.out.println("ChargeDaoImpl couponCheck() Exception -> " + e.getMessage());
			
		}
		
		return checkCouponResult;
	}


	
	
	
	
	
	/*
	private final SqlSession sqlSession;
	
	@Autowired
	public ChargeDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertCharge(ChargeDto bambooChargeDto) {
		System.out.println(bambooChargeDto.toString());
		sqlSession.insert("insertCharge", bambooChargeDto);
	}

	
	*/
	
	
	
	
	
	
	
	
	/*
	쿠폰 적용가능 체크 로직
	@Override
	public List<String> selectCoupon(String couponNo) {
		System.out.println(couponNo);
		List<String> list = sqlSession.selectList("insertCharge", couponNo);
		return list;
	}
	 */
	
	


}
