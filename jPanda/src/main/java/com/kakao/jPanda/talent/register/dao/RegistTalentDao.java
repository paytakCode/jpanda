package com.kakao.jPanda.talent.register.dao;

import java.util.List;

import com.kakao.jPanda.talent.register.domain.CategoryDto;
import com.kakao.jPanda.talent.register.domain.TalentDto;

public interface RegistTalentDao {

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
