package com.kakao.jPanda.member.domain;

import lombok.Data;

@Data
public class MemberDto {
	
	private String memberId;
	private String password;
	private String name;
	private String tel;
	private String email;
	private String birth;
	private String gender;
	private String memberStatus;
	private String memberGrade;
	private String account;
	private String bankCode;
	
	private String year;
	private String month;
	private String day;
}