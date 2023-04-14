package com.kakao.jPanda.jst.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeListDto {
	
	private String status;
	private String title;
	private Long saleBamboo;
	private String regDate;
	private Long sellCount;
	private String purchaseDate;
	private Long refundableDate;
	private String refundStatus;
	private String submitDate;
	private String approveDate;
	private String buyerId;
	private String sellerId;
	private String listType;
	private String talentNo;
	private String purchaseNo;
	private String exchangeStatus;
}
