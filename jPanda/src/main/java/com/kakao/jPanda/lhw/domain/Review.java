package com.kakao.jPanda.lhw.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
	
	private Long reviewNo;
	private Long talentNo;
	private String reviewerId;
	private String content;
	private Date reviewDate;
	private Double bambooScore;

}
