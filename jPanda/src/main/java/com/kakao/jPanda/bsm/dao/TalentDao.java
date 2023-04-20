package com.kakao.jPanda.bsm.dao;

import java.util.List;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

public interface TalentDao {

	List<Category> 	selectCategorys();
	int		 		insertTalent(Talent talent);
	Talent 			selectTalentBytalentNo(Long talentNo);
	int 			updateTalent(Talent talent);
	List<Talent> selectBestSellerTalents();
	List<Talent> selectTopRatedTalentTalents();
	List<Talent> selectNewTrendTalents();
	List<Talent> selectRandomTalents();

}
