package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {

	private final SqlSession sqlSession;
	
	// 재능 전체 리스트
	@Override
	public List<Talent> selectTalentList() {
		return sqlSession.selectList("selectTalentList");
	}
	
	// 공지사항 전체 리스트
	@Override
	public List<Notice> selectNoticeList() {
		List<Notice> list = sqlSession.selectList("selectNoticeList"); 
		return list;
	}
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	@Override
	public List<Category> selectUpperCategoryList() {
		return sqlSession.selectList("selectUpperCategoryList");
	}
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	@Override
	public List<Talent> selectTalentListByUpperCategoryNo(Long upperCategoryNo) {
		return sqlSession.selectList("selectTalentListByUpperCategoryNo", upperCategoryNo);
	}
	
	// 중분류 카테고리 리스트 불러오기
	@Override
	public List<Category> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) {
		return sqlSession.selectList("selectLowerCategoryListByUpperCategoryNo", upperCategoryNo);
	}
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	@Override
	public List<Talent> selectTalentListByLowerCategoryNo(Long lowerCategoryNo) {
		return sqlSession.selectList("selectTalentListByLowerCategoryNo", lowerCategoryNo);
	}
	
	// 대분류 카테고리 눌렀을 때 필터 기능
	@Override
	public List<Talent> selectTalentListByUpperCategoryAndFilter(Filters filter) {
		return sqlSession.selectList("selectTalentListByUpperCategoryAndFilters", filter);
	}

}
