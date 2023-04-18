package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	
	private final TalentDao talentDao;
	
	// 재능 전체 리스트
	@Override
	public List<Talent> findTalentList() {
		return talentDao.selectTalentList();
	}
	
	// 사이드바 클릭 시 상위 카테고리 리스트 불러오기
	@Override
	public List<Talent> findTalentListByUpperCategoryNo(Long upperCategoryNo) {
		return talentDao.selectTalentListByUpperCategoryNo(upperCategoryNo);
	}
	
	// 중분류 클릭시 해당 카테고리 리스트 불러오기
	@Override
	public List<Talent> findTalentListByLowerCategoryNo(Long lowerCategoryNo) {
		return talentDao.selectTalentListByLowerCategoryNo(lowerCategoryNo);
	}

	@Override
	public Talent findTalentByTalentNo(Long talentNo) {
		return talentDao.selectTalentByTalentNo(talentNo);
	}
}
