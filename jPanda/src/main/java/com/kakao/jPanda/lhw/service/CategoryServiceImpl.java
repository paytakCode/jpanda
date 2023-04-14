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
	
	@Override
	public List<Category> getCategory(Long categoryNo) {
		return categoryDao.getCategory(categoryNo);
	}

}
