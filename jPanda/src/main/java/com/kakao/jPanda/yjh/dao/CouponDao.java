package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Coupon;

public interface CouponDao {
	List<Coupon> findCouponList();
	void insertCoupon(Coupon coupon);
}
