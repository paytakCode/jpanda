package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.CategoryDto;
import com.kakao.jPanda.lhw.domain.FiltersDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

public interface BoardService {
	
	// 사이드바 대분류 카테고리 리스트
	List<CategoryDto> findUpperCategoryList();
	
	// 중분류 카테고리 리스트 불러오기 
	List<CategoryDto> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);
	
	// 재능 리스트 필터 기능
	List<TalentDto> findTalentListByFilter(FiltersDto filters);
	

}
