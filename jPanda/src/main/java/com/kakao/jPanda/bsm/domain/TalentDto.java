package com.kakao.jPanda.bsm.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TalentDto {
	private Long 		talentNo;
	private String 		sellerId;
	private Long 		upperCategoryNo;
	private Long 		lowerCategoryNo;
	private String 		mainImg;
	private String 		title;
	private String  	content;
	private Long  		bamboo;
	private Long  		saleBamboo;
	private String 		summary;
	private String 		talentStatus;
	private Long  		viewCount;
	private Timestamp 	regDate;
	private Timestamp 	statusDate;
			
	private String 		name;
	private double 		reviewAvgScore;
	private Long 		reviewCount;
	private String 		item;
}
