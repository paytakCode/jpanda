package com.kakao.jPanda.kts.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String password;
	private String name;
	private String tel;
	private String email;
	private Date birth;
	private String gender;
	private String memberStatus;
	private String memberGrade;
	private String account;
}
