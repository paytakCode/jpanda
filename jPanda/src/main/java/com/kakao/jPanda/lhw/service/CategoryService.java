package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;


public interface CategoryService {
	List<Category> getCategory(Long categoryNo);
}
