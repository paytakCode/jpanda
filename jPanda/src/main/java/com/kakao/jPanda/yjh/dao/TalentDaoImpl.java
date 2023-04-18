package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TalentDaoImpl implements TalentDao {
	private final SqlSession sqlSession;
	
	@Override
	public List<Talent> selectTalent() {
		
		return sqlSession.selectList("selectTalent");
	}

}
