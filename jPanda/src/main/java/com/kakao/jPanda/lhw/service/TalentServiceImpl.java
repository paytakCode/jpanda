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
	
	@Override
	public List<TalentForBoard> getTalentList() {
		return talentDao.getTalentList();
	}

	@Override
	public List<Notice> getNoticeList() {
		return talentDao.getNoticeList();
	}

	@Override
	public List<Category> getCategory() {
		return talentDao.getCategory();
	}

	@Override
	public List<Talent> getUpperCategoryList(Long upperCategoryNo) {
		return talentDao.getUpperCategoryList(upperCategoryNo);
	}

}
