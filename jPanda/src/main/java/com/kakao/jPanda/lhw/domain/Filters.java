package com.kakao.jPanda.lhw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Filters {
	
	private Long lowerCategoryNo;
	private Long upperCategoryNo;
	private String filter;
}
