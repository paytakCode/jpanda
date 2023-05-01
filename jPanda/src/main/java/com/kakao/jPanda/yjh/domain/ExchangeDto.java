package com.kakao.jPanda.yjh.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeDto {
	private Long exchangeNo;
	private String sellerId;
	private Long talentNo;
	private Long sales;
	private Date submitDate;
	private String approveDate;
	private Long total;
	private String memberGrade;
	private Long exchangeRatio;
	private String exchangeStatus;
	private Long paymentAmount;
}
