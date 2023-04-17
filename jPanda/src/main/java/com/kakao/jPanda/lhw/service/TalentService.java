package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.domain.TalentForBoard;

public interface TalentService {

	// 재능 게시판 전체 리스트
	List<TalentForBoard> getTalentList();

	// 공지사항 전체 리스트
	List<Notice> getNoticeList();

	// 사이드바 상위 카테고리 리스트
	List<Category> getCategory();
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	List<Talent> getUpperCategoryList(Long upperCategoryNo);
	
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	List<TalentForBoard> realGetAjaxLowerList(Long lowerCategoryOne);


}
