package com.kakao.jPanda.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.board.dao.BoardDao;
import com.kakao.jPanda.board.domain.CategoryDto;
import com.kakao.jPanda.board.domain.FiltersDto;
import com.kakao.jPanda.board.service.BoardService;
import com.kakao.jPanda.talent.detail.domain.TalentDto;

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
		// 필터 걸린 리스트
		List<TalentDto> talentListByFilters = boardDao.selectTalentListByFilter(filters);
		// 페이징용 토탈 카운트
		filters.setTotalCount(talentListByFilters.size());
		log.info("talentList Total Count-> "+ talentListByFilters.size());
		// 카운트와 리스트 다시 가져오기
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