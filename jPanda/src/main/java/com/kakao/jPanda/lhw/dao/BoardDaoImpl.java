package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.CategoryDto;
import com.kakao.jPanda.lhw.domain.FiltersDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

import lombok.RequiredArgsConstructor;

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
		System.out.println("Filter -> " + filters.getFilter());
		System.out.println("UpperCategoryNo -> " + filters.getUpperCategoryNo());
		System.out.println("LowerCategoryNo -> " + filters.getLowerCategoryNo());
		return sqlSession.selectList("selectTalentListByFilters", filters);
	}
	

}
