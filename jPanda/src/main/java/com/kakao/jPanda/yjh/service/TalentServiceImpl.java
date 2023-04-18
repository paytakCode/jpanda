package com.kakao.jPanda.yjh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.TalentDao;
import com.kakao.jPanda.yjh.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	private final TalentDao talentDao;

	@Override
	public List<Talent> findTalent() {
		List<Talent> talentList = talentDao.selectTalent();
		return talentList;
	}

}
