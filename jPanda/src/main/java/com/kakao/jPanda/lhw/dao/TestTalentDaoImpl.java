package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.TestTalent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestTalentDaoImpl implements TestTalentDao {

	private final SqlSession sqlSession;

	@Override
	public List<TestTalent> selectTestTalentList() {
		return sqlSession.selectList("selectTestTalentList");
	}
	
	
	
}
