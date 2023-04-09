package com.kakao.jPanda.jst.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberJoinDto {
	private String id;
	private String password;
	private String name;
	private String tel;
	private String email;
	private String birth;
	private String gender;
	private String account;
	private String grade;

}
