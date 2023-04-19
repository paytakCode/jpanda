package com.kakao.jPanda.lhw.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestTalent {
	
	private Long talentNo;
	private String sellerId;
	private Long upperCategoryNo;
	private Long lowerCategoryNo;
	private String mainImg;
	private String title;
	private Long content;
	private Long bamboo;
	private Long saleBamboo;
	private String summary;
	private String status;
	private Long viewCount;
	private Date regDate;
	private Date statusDate;
	
	private Long bambooScore;

}
