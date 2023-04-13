package com.kakao.jPanda.lhw.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Talent {
	// 판매 게시판
	private Long talentNo; //talent_no
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
	private Date statusDate;
}
