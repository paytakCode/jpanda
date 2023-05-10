package com.kakao.jPanda.lhw.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.BoardDao;
import com.kakao.jPanda.lhw.domain.CategoryDto;
import com.kakao.jPanda.lhw.domain.FiltersDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	// 재능 리스트 필터 기능
	@Override
	public HashMap<String, Object> findTalentListByFilter(FiltersDto filters) {
		List<TalentDto> talentListByFilters = boardDao.selectTalentListByFilter(filters);
		
		filters.setTotalCount(talentListByFilters.size());
		log.info("talentList Total Count-> "+ talentListByFilters.size());
		talentListByFilters = boardDao.selectTalentListByFilter(filters);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("talentList", talentListByFilters);
		map.put("filters", filters);
		
		return map;
	}
	
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
	


}