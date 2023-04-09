package com.kakao.jPanda.jst.domain;

import lombok.Data;

@Data
public class SellListDto {
	private String status;
	private String title;
	private Long saleBamboo;
	private String regDate;
	private long sellCount;
}
