package com.kakao.jPanda.yjh.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponDto {
	private String couponNo;
	private String issueDate;
	private String expireDate;
	private Long couponValue;
	
	//만료된 쿠폰의 사용횟수 조회용 컬럼 alias
	private String usedCouponCount;
	
	//쿠폰 사용기간 입력 용 필드
	private Timestamp startDate;
	private Timestamp endDate;
}
