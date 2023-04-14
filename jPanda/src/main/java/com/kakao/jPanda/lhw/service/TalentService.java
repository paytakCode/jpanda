package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.domain.TalentForBoard;

public interface TalentService {

	List<TalentForBoard> getTalentList();

	List<Notice> getNoticeList();

	List<Category> getCategory();
	
	List<Talent> getUpperCategoryList(Long upperCategoryNo);

}
