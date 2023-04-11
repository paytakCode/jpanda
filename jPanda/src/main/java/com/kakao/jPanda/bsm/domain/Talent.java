package com.kakao.jPanda.bsm.domain;

import lombok.Data;

@Data
public class Talent {
	private Long 	talentNo;
	private String 	sellerId;
	private int 	upperCategoryNo;
	private int 	lowerCategoryNo;
	private String 	mainImg;
	private String 	title;
	private String  content;
	private int  	bamboo;
	private int  	saleBamboo;
	private String 	summary;
	private String 	status;
	private int  	viewCount;
	private String 	regDate;
}
