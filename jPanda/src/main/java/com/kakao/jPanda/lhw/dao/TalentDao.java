package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Talent;

public interface TalentDao {

	List<Talent> selectTalentList();

	Talent selectTalentByTalentNo(Long talentNo);

	List<Talent> selectTalentListByUpperCategoryNo(Long upperCategoryNo);

	List<Talent> selectTalentListByLowerCategoryNo(Long lowerCategoryNo);
	
	
}
