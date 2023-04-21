package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;


public interface CategoryService {
	
	// 사이드바 대분류 카테고리 리스트
	List<Category> findUpperCategoryList();
	
	// 중분류 카테고리 리스트 불러오기
	List<Category> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);
	
	
}
