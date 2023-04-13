package com.kakao.jPanda.bsm.domain;

import lombok.Data;

@Data
public class Talent {
	private Long 	talentNo;
	private String 	sellerId;
	private Long 	upperCategoryNo;
	private Long 	lowerCategoryNo;
	private String 	mainImg;
	private String 	title;
	private String  content;
	private Long  	bamboo;
	private Long  	saleBamboo;
	private String 	summary;
	private String 	status;
	private Long  	viewCount;
	private String 	regDate;
	private String 	statusDate;
}
