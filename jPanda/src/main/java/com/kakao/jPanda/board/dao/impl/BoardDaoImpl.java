package com.kakao.jPanda.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.board.dao.BoardDao;
import com.kakao.jPanda.board.domain.CategoryDto;
import com.kakao.jPanda.board.domain.FiltersDto;
import com.kakao.jPanda.talent.detail.domain.TalentDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {

	private final SqlSession sqlSession;
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	@Override
	public List<CategoryDto> selectUpperCategoryList() {
		return sqlSession.selectList("selectUpperCategoryList");
	}
	
	// 중분류 카테고리 리스트 불러오기
	@Override 
	public List<CategoryDto> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) { 
		return sqlSession.selectList("selectLowerCategoryListByUpperCategoryNo", upperCategoryNo); 
	}
	
	// 재능 리스트 필터 기능
	@Override
	public List<TalentDto> selectTalentListByFilter(FiltersDto filters) {
		log.info("Filter -> " + filters.getFilter());
		log.info("UpperCategoryNo -> " + filters.getUpperCategoryNo());
		log.info("LowerCategoryNo -> " + filters.getLowerCategoryNo());
		return sqlSession.selectList("selectTalentListByFilters", filters);
	}
	

}
