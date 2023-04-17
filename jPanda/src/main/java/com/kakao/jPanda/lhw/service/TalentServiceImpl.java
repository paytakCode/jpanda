package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.domain.TalentForBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	
	private final TalentDao talentDao;
	
	// 재능 게시판 전체 리스트
	@Override
	public List<TalentForBoard> getTalentList() {
		return talentDao.getTalentList();
	}
	
	// 공지사항 전체 리스트
	@Override
	public List<Notice> getNoticeList() {
		return talentDao.getNoticeList();
	}
	
	// 사이드바 상위 카테고리 리스트
	@Override
	public List<Category> getCategory() {
		return talentDao.getCategory();
	}
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	@Override
	public List<Talent> getUpperCategoryList(Long upperCategoryNo) {
		return talentDao.getUpperCategoryList(upperCategoryNo);
	}
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	@Override
	public List<TalentForBoard> realGetAjaxLowerList(Long lowerCategoryOne) {
		return talentDao.realGetAjaxLowerList(lowerCategoryOne);
	}

}
