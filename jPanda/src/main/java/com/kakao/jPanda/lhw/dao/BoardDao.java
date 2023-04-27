package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

public interface BoardDao {
	
	// 공지사항 전체 리스트
	List<Notice> selectNoticeList();
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	List<Category> selectUpperCategoryList();
	
	// 중분류 카테고리 리스트 불러오기 
	List<Category> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);
	 
	// 재능 리스트 필터 기능
	List<Talent> selectTalentListByFilter(Filters filters);
	

}
