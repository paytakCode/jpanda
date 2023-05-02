package com.kakao.jPanda.bsm.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NoticeDto {
	private Long 		noticeNo;
	private String 		title;
	private String 		content;
	private Timestamp 	regDate;
}
