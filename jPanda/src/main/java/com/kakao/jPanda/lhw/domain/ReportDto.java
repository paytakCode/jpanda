package com.kakao.jPanda.lhw.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportDto {
	
	private Long reportNo;
	private String blackId;
	private String reportId;
	private String issue;
	private Timestamp reportDate;
	private Long talentNo;
	private Long chatNo;
}
