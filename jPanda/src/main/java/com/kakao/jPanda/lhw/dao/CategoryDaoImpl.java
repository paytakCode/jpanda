package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Category;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {
	
	private final SqlSession sqlSession;
	
	@Override
	public List<Category> selectUpperCategoryList() {
		return sqlSession.selectList("selectUpperCategoryList");
	}
	
	@Override
	public List<Category> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) {
		return sqlSession.selectList("selectLowerCategoryListByUpperCategoryNo", upperCategoryNo);
	}

	
}
