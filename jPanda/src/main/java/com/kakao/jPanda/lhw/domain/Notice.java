package com.kakao.jPanda.lhw.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Notice {
	// 공지사항 게시판
	private int noticeNo;
	private String title;
	private String content;
	private Date regDate;

}
