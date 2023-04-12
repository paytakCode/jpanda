package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final TalentDao talentDao;
	
	@Override
	public List<Talent> getTalentList() {
		return talentDao.getTalentList();
	}

	@Override
	public List<Notice> getNoticeList() {
		return talentDao.getNoticeList();
	}

}
