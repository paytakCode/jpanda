package com.kakao.jPanda.yjh.domain;

import java.util.Date;
import java.util.List;

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
	//Member table
	private String id;
	private String password;
	private String name;
	private String tel;
	private String email;
	private String birth;
	private String gender;
	private String status;
	private String grade;
	
	//Bamboo_charge table
	private Long chargeNo;
	private String chargerId;
	private String paymentMethod;
	private Long paymentAmount;	
	private Long chargeBamboo;
	private String chargeDate;
	private String couponNo;
	
	//Exchange table(id, grade, status, paymentAmount alias 확인후 작업하기)
	private Long exchangeNo;
	private String exchangeId;
	private Long talentNo;
	private Long sales;
	private Date submitDate;
	private String approveDate;
	private Long total;
	private String exchangeGrade;
	private Long ratio;
	private String exchangeStatus;
	private Long exchangePaymentAmount;
	
	//기준 날짜
	public String startDate;
	public String endDate;
	
	//결과 값
	private Long count;
	private String yearData;
}
