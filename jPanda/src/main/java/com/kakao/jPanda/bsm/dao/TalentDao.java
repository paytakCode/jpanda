package com.kakao.jPanda.bsm.dao;

import java.util.List;

import com.kakao.jPanda.bsm.domain.CategoryDto;
import com.kakao.jPanda.bsm.domain.TalentDto;

public interface TalentDao {

	List<CategoryDto> 	selectCategorys();
	int		 		insertTalent(TalentDto talent);
	TalentDto 			selectTalentBytalentNo(Long talentNo);
	int 			updateTalent(TalentDto talent);
	List<TalentDto> 	selectBestSellerTalents();
	List<TalentDto> 	selectTopRatedTalents();
	List<TalentDto> 	selectNewTrendTalents();
	List<TalentDto> 	selectRandomTalents();
	String selectSellerIdByTalent(TalentDto talent);

}
