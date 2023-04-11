package com.kakao.jPanda.jst.domain;

import lombok.Data;

@Data
public class StatDto {
	private Long sellCount;
	private Long soldCount;
	private Long buyCount;
	private Long refundSubmitCount;
	private Long refundApproveCount;
	private Long refundRejectCount;
}
