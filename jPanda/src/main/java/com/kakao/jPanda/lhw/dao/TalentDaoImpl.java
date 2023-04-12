package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TalentDaoImpl implements TalentDao {

	private final SqlSession sqlSession;
	
	@Override
	public List<Talent> getTalentList() {
		return sqlSession.selectList("talentList");
	}

	@Override
	public List<Notice> getNoticeList() {
		return sqlSession.selectList("noticeList");
	}
}
