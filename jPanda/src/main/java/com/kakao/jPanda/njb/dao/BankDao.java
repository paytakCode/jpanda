package com.kakao.jPanda.njb.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.njb.domain.Bank;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BankDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Bank> getBankList() {
	    return sqlSession.selectList("getBankList");
	}
}


