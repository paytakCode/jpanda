package com.kakao.jPanda.yjh.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Exchange {
	private Long exchangeNo;
	private String id;
	private Long talentNo;
	private Long sales;
	private Date submitDate;
	private String approveDate;
	private Long total;
	private String grade;
	private Long ratio;
	private String status;
	private Long paymentAmount;
}
