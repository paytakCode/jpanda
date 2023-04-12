package com.kakao.jPanda.jst.domain;

import lombok.Data;

@Data
public class BuyListDto {
	private String status;
	private String title;
	private Long saleBamboo;
	private String purchaseDate;
	private Long refundableDate;
}
