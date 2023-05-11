package com.kakao.jPanda.admin.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
	private String couponCode;
	private Timestamp issueDate;
	private Timestamp expireDate;
	private Long couponValue;
	
	//만료된 쿠폰의 사용횟수 조회용 컬럼 alias
	private String usedCouponCount;

}
