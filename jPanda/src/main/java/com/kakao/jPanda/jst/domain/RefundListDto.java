package com.kakao.jPanda.jst.domain;

import lombok.Data;

@Data
public class RefundListDto {
	private String status;
	private String title;
	private Long saleBamboo;
	private String submitDate;
	private String approveDate;
}
