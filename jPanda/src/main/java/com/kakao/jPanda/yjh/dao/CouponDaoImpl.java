package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.Coupon;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CouponDaoImpl implements CouponDao {
	private final SqlSession sqlSession;
	
	@Override
	public List<Coupon> findCouponList() {
		System.out.println("===== CouponRepository findCouponList() start =====");
		List<Coupon> couponList = null;
		try {
			couponList = sqlSession.selectList("findCouponList");
			
		} catch(Exception e) {
			System.out.println("CouponRepository findCouponList() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return couponList;
	}

	@Override
	public void insertCoupon(Coupon coupon) {
		System.out.println("===== CouponRepository insertCoupon() start =====");
		sqlSession.insert("insertCoupon", coupon);
	}

}
