package com.kakao.jPanda.njb.domain;

import java.util.Date;

import lombok.Data;


@Data
public class Member {
	
	private String id;
	private String password;
	private String name;
	private String tel;
	private String email;
//	private Date birth;
	private String gender;
	private String status;
	private String grade;
	private String account;
	
}
