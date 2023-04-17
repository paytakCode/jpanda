package com.kakao.jPanda.yjh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.Coupon;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CouponDaoImpl implements CouponDao {
	private final SqlSession sqlSession;
	
	@Override
	public void insertCouponData(Coupon coupon) {
		System.out.println("===== CouponRepository insertCouponData() start =====");
		sqlSession.insert("insertCouponData", coupon);
		
	}

}
