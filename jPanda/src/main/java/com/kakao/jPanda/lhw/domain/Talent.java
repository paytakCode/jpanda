package com.kakao.jPanda.lhw.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Talent {
	// 판매 게시판
	private int talentNo; //talent_no
	private String sellerId;
	private int upperCategoryNo;
	private int lowerCategoryNo;
	private String mainImg;
	private String title;
	private String content;
	private int bamboo;
	private int saleBamboo;
	private String summary;
	private String status;
	private int viewCount;
	private Date regDate;
	private Date statusDate;
}
