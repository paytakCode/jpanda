package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

public interface BoardService {

	// 재능 전체 리스트
	List<Talent> findTalentList();
	
	// 공지사항 전체 리스트
	List<Notice> findNoticeList();
	
	// 사이드바 대분류 카테고리 리스트
	List<Category> findUpperCategoryList();
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	List<Talent> findTalentListByUpperCategoryNo(Long upperCategoryNo);
	
	// 중분류 카테고리 리스트 불러오기
	List<Category> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	List<Talent> findTalentListByLowerCategoryNo(Long lowerCategoryNo);
	
	// 대분류 카테고리 필터 기능
	List<Talent> findTalentListByUpperCategoryAndFilter(Filters filters);
}
