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
	public List<Category> getCategory(Long categoryNo) {
		return sqlSession.selectList("getAllCategory", categoryNo);
	}

	@Override
	public List<Category> getLowerCategory(Long upperCategoryNo) {
		return sqlSession.selectList("getLowerCategory", upperCategoryNo);
	}

}
