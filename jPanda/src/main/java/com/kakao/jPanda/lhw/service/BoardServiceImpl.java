package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.BoardDao;
import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	// 공지사항 리스트 
	@Override
	public List<Notice> findNoticeList() {
		List<Notice> list = boardDao.selectNoticeList();
		return list;
	}
	
	// 사이드바 대분류 카테고리 리스트 불러오기
	@Override
	public List<Category> findUpperCategoryList() {
		return boardDao.selectUpperCategoryList();
	}
	
	// 중분류 카테고리 리스트 불러오기
	@Override 
	public List<Category> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) { 
		return boardDao.selectLowerCategoryListByUpperCategoryNo(upperCategoryNo); 
	}
	
	// 재능 리스트 필터 기능
	@Override
	public List<Talent> findTalentListByFilter(Filters filters) {
		return boardDao.selectTalentListByFilter(filters);
	}
	


}