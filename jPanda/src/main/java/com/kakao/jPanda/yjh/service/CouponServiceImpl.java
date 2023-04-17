package com.kakao.jPanda.yjh.service;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.CouponDao;
import com.kakao.jPanda.yjh.domain.Coupon;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
	private final CouponDao couponDao;
	
	@Override
	public void insertCouponData(Coupon coupon) {
		System.out.println("===== CouponService insertCouponData() start =====");
		couponDao.insertCouponData(coupon);
		
	}

}
