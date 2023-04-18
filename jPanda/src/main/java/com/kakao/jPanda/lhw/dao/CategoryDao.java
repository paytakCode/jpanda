package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;

public interface CategoryDao {
	
	List<Category> selectUpperCategoryList();

	List<Category> selectLowerCategoryListByUpperCategoryNo(Long upperCategoryNo);

	
}
