package com.kakao.jPanda.lhw.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.Criteria;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	
	private final TalentDao talentDao;
	
	// 재능 전체 리스트
	@Override
	public List<Talent> findTalentListByCriteria(Criteria criteria) {
		List<Talent> selectTalentListByCriteria = Collections.emptyList();
		
		int findTalentTotalCountByCriteria = talentDao.selectTalentTotalCountByCriteria(criteria);
		
		if(findTalentTotalCountByCriteria > 0) {
			selectTalentListByCriteria = talentDao.selectTalentListByCriteria(criteria);
		}
		
		return selectTalentListByCriteria;
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

	@Override
	public int findTalentTotalCountByCriteria(Criteria criteria) {
		return talentDao.selectTalentTotalCountByCriteria(criteria);
	}

}