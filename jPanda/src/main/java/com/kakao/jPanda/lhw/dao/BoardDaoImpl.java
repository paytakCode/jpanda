package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {

	private final SqlSession sqlSession;
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	@Override
	public List<Category> selectUpperCategoryList() {
		return sqlSession.selectList("selectUpperCategoryList");
	}
	
	// 중분류 카테고리 리스트 불러오기
	@Override 
	public List<Category> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) { 
		return sqlSession.selectList("selectLowerCategoryListByUpperCategoryNo", upperCategoryNo); 
	}
	
	// 재능 리스트 필터 기능
	@Override
	public List<Talent> selectTalentListByFilter(Filters filters) {
		System.out.println("DAO -> " + filters.getFilter());
		System.out.println("DAO -> " + filters.getUpperCategoryNo());
		System.out.println("DAO -> " + filters.getLowerCategoryNo());
		return sqlSession.selectList("selectTalentListByFilters", filters);
	}
	

}
