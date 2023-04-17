package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.domain.TalentForBoard;

public interface TalentDao {

	List<TalentForBoard> getTalentList();

	List<Notice> getNoticeList();

	List<Category> getCategory();

	Talent getTalentView(Long talentNo);

	List<Talent> getUpperCategoryList(Long upperCategoryNo);

	List<TalentForBoard> realGetAjaxLowerList(Long lowerCategoryOne);

}
