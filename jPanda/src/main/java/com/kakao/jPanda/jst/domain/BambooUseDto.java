package com.kakao.jPanda.jst.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BambooUseDto {
	private Long purchaseNo;
	private String buyerId;
	private Long talentNo;
	private Date purchaseDate;
	private Long useBamboo;
}
