package com.kakao.jPanda.yjh.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
	private Long noticeNo;
	private String title;
	private String content;
	private Timestamp regDate;
}
