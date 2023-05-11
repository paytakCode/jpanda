package com.kakao.jPanda.member.domain;

import lombok.Data;

@Data
public class EmailVerifDto {
	
	private String email;
	private String verifCode;
}
