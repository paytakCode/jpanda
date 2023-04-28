package com.kakao.jPanda.bsm.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Notice {
	private Long 		noticeNo;
	private String 		title;
	private String 		content;
	private Timestamp 	regDate;
}
