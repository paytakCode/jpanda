package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

public interface BoardDao {
	// 재능 전체 리스트
	List<Talent> selectTalentList();
	
	// 공지사항 전체 리스트
	List<Notice> selectNoticeList();
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	List<Category> selectUpperCategoryList();
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	List<Talent> selectTalentListByUpperCategoryNo(Long upperCategoryNo);

	// 중분류 카테고리 리스트 불러오기
	List<Category> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	List<Talent> selectTalentListByLowerCategoryNo(Long lowerCategoryNo);
	
	// 대분류 카테고리 눌렀을 때 필터 기능
	List<Talent> selectTalentListByUpperCategoryAndFilter(Filters filters);
}
