package com.kakao.jPanda.yjh.service;

public interface CouponService {
	String generateCouponNo();
	void addCoupon(String couponValue, String couponNo);
}
