package com.kakao.jPanda.bsm.domain;



import lombok.Data;

@Data
public class Category {
	private Long categoryNo;
	private String item;
	private Long upperCategoryNo;
}
