package com.kakao.jPanda.yjh.domain;

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
public class CompanySalesDto {
	//Bamboo_charge table
	private Long chargeNo;
	private String chargerId;
	private String paymentMethod;
	private Long paymentAmount;	
	private Long chargeBamboo;
	private Timestamp chargeDate;
	private String couponNo;
	
	//Exchange table(id, grade, status, paymentAmount alias 확인후 작업하기)
	private Long exchangeNo;
	private String exchangeId;
	private Long talentNo;
	private Long sales;
	private Timestamp submitDate;
	private String approveDate;
	private Long total;
	private String exchangeGrade;
	private Long ratio;
	private String exchangeStatus;
	private Long exchangePaymentAmount;
	
	//기준 날짜
	private Timestamp startDate;
	private Timestamp endDate;
	
	//결과 값
	private Long bcCount;
	private String period;
	private Long exCount;
	
}
