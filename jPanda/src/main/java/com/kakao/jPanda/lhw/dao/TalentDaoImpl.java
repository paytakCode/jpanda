package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.domain.TalentForBoard;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TalentDaoImpl implements TalentDao {

	private final SqlSession sqlSession;
	
	@Override
	public List<TalentForBoard> getTalentList() {
		return sqlSession.selectList("talentList");
	}

	@Override
	public List<Notice> getNoticeList() {
		return sqlSession.selectList("noticeList");
	}

	@Override
	public List<Category> getCategory() {
		return sqlSession.selectList("getCategoryNo");
	}

	@Override
	public Talent getTalentView(Long talentNo) {
		return sqlSession.selectOne("getTalentView", talentNo);
	}

	@Override
	public List<Talent> getUpperCategoryList(Long upperCategoryNo) {
		return sqlSession.selectList("getUpperCategoryList", upperCategoryNo);
	}

	@Override
	public List<TalentForBoard> realGetAjaxLowerList(Long lowerCategoryOne) {
		return sqlSession.selectList("realGetAjaxLowerList", lowerCategoryOne);
	}
}
