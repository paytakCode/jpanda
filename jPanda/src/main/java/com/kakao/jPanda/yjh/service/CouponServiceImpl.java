package com.kakao.jPanda.yjh.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.CouponDao;
import com.kakao.jPanda.yjh.domain.Coupon;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
	private final CouponDao couponDao;
	
	@Override
	public String generateCouponNo(){
		System.out.println("===== CouponService generateCouponNo() start =====");
		List<Coupon> couponList = null;
		String couponNo = null;
		UUID uuid = null;
		boolean result = false;
		
		do {
			uuid = UUID.randomUUID();
			couponNo = uuid.toString().substring(0, 6);
			System.out.println(couponNo);
			couponList = couponDao.findCouponList();
			System.out.println("couponList : "+couponList.toString());
			
			for(Coupon cn : couponList) {
				if(cn.getCouponNo().equals(couponNo)) {
					result = false;
				} else {
					result = true;
				}
			}
		} while(!result);
		
		return couponNo;
	}

	@Override
	public void addCoupon(String couponValue, String couponNo) {
		System.out.println("===== CouponService addCoupon() start =====");
		Long longCouponValue = Long.parseLong(couponValue);
		System.out.println("longCouponNo : "+longCouponValue.toString());
		
//		Coupon coupon = null;
		Coupon coupon = new Coupon();
		coupon.setCouponNo(couponNo);
		coupon.setCouponValue(longCouponValue);
		System.out.println("coupon : "+coupon.toString());
		
		couponDao.insertCoupon(coupon);

	}

}
