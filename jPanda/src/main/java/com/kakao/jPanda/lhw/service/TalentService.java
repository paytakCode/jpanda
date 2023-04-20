package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Talent;

public interface TalentService {

	// 재능 전체 리스트
	List<Talent> findTalentList();
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	List<Talent> findTalentListByUpperCategoryNo(Long upperCategoryNo);
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	List<Talent> findTalentListByLowerCategoryNo(Long lowerCategoryNo);
	
	// 재능 상세 페이지 
	Talent findTalentByTalentNo(Long talentNo);

}
