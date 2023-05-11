package com.kakao.jPanda.trade.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TalentDto {
	private String talentNo;
	private String sellerId;
	private String upperCategoryNo;
	private String lowerCategoryNo;
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
	
	//mapper.xml selectTalentByTalentNo
	private Long exchangeNo;
}
