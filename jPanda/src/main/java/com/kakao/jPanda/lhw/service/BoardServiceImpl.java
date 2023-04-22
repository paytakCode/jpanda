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
	
	// 재능 전체 리스트
	@Override
	public List<Talent> findTalentList() {
		return boardDao.selectTalentList();
	}
	
	// 노티스 리스트 
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
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	@Override
	public List<Talent> findTalentListByUpperCategoryNo(Long upperCategoryNo) {
		return boardDao.selectTalentListByUpperCategoryNo(upperCategoryNo);
	}
	
	// 중분류 카테고리 리스트 불러오기
	@Override
	public List<Category> findLowerCategoryListByUpperCategoryNo(Long upperCategoryNo) {
		return boardDao.selectLowerCategoryListByUpperCategoryNo(upperCategoryNo);
	}
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	@Override
	public List<Talent> findTalentListByLowerCategoryNo(Long lowerCategoryNo) {
		return boardDao.selectTalentListByLowerCategoryNo(lowerCategoryNo);
	}
	
	// 대분류 카테고리 눌렀을 때 필터 기능
	public List<Talent> findTalentListByUpperCategoryAndFilter(Filters filters){
		return boardDao.selectTalentListByUpperCategoryAndFilter(filters);
	}

}