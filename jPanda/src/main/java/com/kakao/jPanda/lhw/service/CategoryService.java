package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;


public interface CategoryService {
	
	// 대분류 카테고리 리스트 가져오기
	List<Category> getCategory(Long categoryNo);
	
	// 중분류 카테고리 리스트 불러오기
	List<Category> getLowerCategory(Long upperCategoryNo);
	
}
