package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TestTalentDao;
import com.kakao.jPanda.lhw.domain.TestTalent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestTalentServiceImpl implements TestTalentService {
	
	private final TestTalentDao testTalentDao;
	
	@Override
	public List<TestTalent> findTestTalentList() {
		return testTalentDao.selectTestTalentList();
	}

}
