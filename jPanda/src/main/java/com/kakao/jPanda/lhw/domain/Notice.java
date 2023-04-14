package com.kakao.jPanda.lhw.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
	// 공지사항 게시판
	private Long noticeNo;
	private String title;
	private String content;
	private Date regDate;

}
