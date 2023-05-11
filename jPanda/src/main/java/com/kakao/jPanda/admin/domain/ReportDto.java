package com.kakao.jPanda.admin.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
	private Long reportNo;
	private String blackId;
	private String reportId;
	private String issue;
	private Timestamp reportDate;
	private Long talentNo;
	
	//member테이블 조인용
	private String memberId;
	
	//권한 변경용 컬럼
	private String memberStatus;
	
	//group 함수 데이터 조회용
	private String reportCount;
}
