package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.CategoryDao;
import com.kakao.jPanda.lhw.domain.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryDao categoryDao;
	
	// 사이드바 대분류 카테고리 리스트
	@Override
	public List<Category> findUpperCategoryList() {
		return categoryDao.selectUpperCategoryList();
	}
	
	@Override
	public List<Category> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) {
		return categoryDao.selectLowerCategoryListByUpperCategoryNo(upperCategoryNo);
	}

	
}
