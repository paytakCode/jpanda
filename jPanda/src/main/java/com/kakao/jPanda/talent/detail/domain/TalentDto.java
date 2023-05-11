package com.kakao.jPanda.talent.detail.domain;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TalentDto {
	// 판매 게시판
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
	private String talentStatus;
	private Long viewCount;
	private Timestamp regDate;
	private Timestamp statusDate;
	
	// 리뷰 테이블용 
	private Double bambooScore;
	private int reviewCount;
	
	// 이름 조회용
	private String name;
}
