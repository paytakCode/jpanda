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
@AllArgsConstructor
@NoArgsConstructor
public class TalentRefundDto {	
	private Long purchaseNo;
	private String refundStatus;
	private String issue;
	private Timestamp submitDate;
	private Timestamp approveDate;
	private Long refundBamboo;
	
	//bamboo_use table join용
	private String buyerId;
	private Long talentNo;
	private String listType;
}
