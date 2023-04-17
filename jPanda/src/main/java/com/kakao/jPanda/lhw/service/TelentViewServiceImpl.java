package com.kakao.jPanda.lhw.service;


import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelentViewServiceImpl implements TalentViewService {
	
	private final TalentDao talentDao;
	
	@Override
	public Talent getTalentView(Long talentNo) {

		return talentDao.getTalentView(talentNo);
	}

}
