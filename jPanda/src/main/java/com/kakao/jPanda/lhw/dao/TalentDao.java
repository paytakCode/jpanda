package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Criteria;
import com.kakao.jPanda.lhw.domain.Talent;

public interface TalentDao {

	List<Talent> selectTalentListByCriteria(Criteria criteria);

	Talent selectTalentByTalentNo(Long talentNo);

	List<Talent> selectTalentListByUpperCategoryNo(Long upperCategoryNo);

	List<Talent> selectTalentListByLowerCategoryNo(Long lowerCategoryNo);
	
	// 페이징 카운트
	int selectTalentTotalCountByCriteria(Criteria criteria);
	
}
