package com.kakao.jPanda.njb.domain;

import lombok.Data;

@Data
public class EmailVerifDto {
	private String email;
	private String verifCode;
}
