package com.kakao.jPanda.bsm.dao;

import java.util.List;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

public interface TalentDao {

	List<Category> 	selectCategorys();
	void		 	insertTalent(Talent talent);
	Talent 			selectTalentBytalentNo(Long talentNo);
	void 			updateTalent(Talent talent);

}
