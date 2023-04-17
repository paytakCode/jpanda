package com.kakao.jPanda.bsm.dao;

import java.util.List;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

public interface TalentDao {

	List<Category> 	selectCategorys();
	void		 	talentAdd(Talent talent);
	Talent 			getTalent(Long talentNo);
	void 			talentModify(Talent talent);

}
