package com.kakao.jPanda.kts.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Partner {
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String name;
}
