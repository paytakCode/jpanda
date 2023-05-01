package com.kakao.jPanda.lhw.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Review {
	
	private Long reviewNo;
	private Long talentNo;
	private String reviewerId;
	private String content;
	private Timestamp reviewDate;
	private Double bambooScore;

}
