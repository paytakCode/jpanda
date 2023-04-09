package com.kakao.jPanda.jst.domain;

import lombok.Data;

@Data
public class BuyListDto {
	private String title;
	private Long saleBamboo;
	private String regDate;
	private int refundableDate;
}
