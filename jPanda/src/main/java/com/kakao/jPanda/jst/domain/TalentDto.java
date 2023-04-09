package com.kakao.jPanda.jst.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class TalentDto {
	private Long talentNo;
	private String sellerId;
	private Long upperCategoryNo;
	private Long lowerCategoryNo;
	private String mainImg;
	private String title;
	private String content;
	private Long bamboo;
	private Long saleBamboo;
	private String summary;
	private String status;
	private Long viewCount;
	private Date regDate;
}
