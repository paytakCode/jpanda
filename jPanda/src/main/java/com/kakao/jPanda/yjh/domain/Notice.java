package com.kakao.jPanda.yjh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {
	private Long noticeNo;
	private String title;
	private String content;
	private String regDate;
}
