package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.BoardDao;
import com.kakao.jPanda.lhw.domain.CategoryDto;
import com.kakao.jPanda.lhw.domain.FiltersDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	@Override
	public List<CategoryDto> findUpperCategoryList() {
		return boardDao.selectUpperCategoryList();
	}
	
	// 중분류 카테고리 리스트 불러오기
	@Override 
	public List<CategoryDto> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) { 
		return boardDao.selectLowerCategoryListByUpperCategoryNo(upperCategoryNo); 
	}
	
	// 재능 리스트 필터 기능
	@Override
	public List<TalentDto> findTalentListByFilter(FiltersDto filters) {
		return boardDao.selectTalentListByFilter(filters);
	}
	


}