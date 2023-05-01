package com.kakao.jPanda.yjh.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
	private String talentStatus;
	private Long viewCount;
	private String regDate;
	private String statusDate;
}
