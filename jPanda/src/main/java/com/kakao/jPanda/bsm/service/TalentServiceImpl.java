package com.kakao.jPanda.bsm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.bsm.dao.TalentDao;
import com.kakao.jPanda.bsm.domain.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService{
	private final TalentDao dao;
	@Override
	public List<Category> categoryList() {
		List<Category> categoryList = dao.categoryList();
		return categoryList;
	}

}
