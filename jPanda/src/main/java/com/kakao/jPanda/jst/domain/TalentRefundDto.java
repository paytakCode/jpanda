package com.kakao.jPanda.jst.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TalentRefundDto {
	private Long purchaseNo;
	private String status;
	private String issue;
	private Date submitDate;
	private Date approveDate;
	private Long refundBamboo;
	
}
