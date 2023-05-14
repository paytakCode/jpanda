package com.kakao.jPanda.board.service;

import java.util.HashMap;
import java.util.List;

import com.kakao.jPanda.board.domain.CategoryDto;
import com.kakao.jPanda.board.domain.FiltersDto;

public interface BoardService {
	
	// 재능 리스트 필터 기능
	HashMap<String, Object> findTalentListByFilter(FiltersDto filters);
	
	// 사이드바 대분류 카테고리 리스트
	List<CategoryDto> findUpperCategoryList();
	
	// 중분류 카테고리 리스트 불러오기 
	List<CategoryDto> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);
	

}
