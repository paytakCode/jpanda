package com.kakao.jPanda.njb.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CheckIdDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int checkId(String id) {
		return sqlSession.selectOne("checkId", id);
	}


	
}