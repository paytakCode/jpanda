package com.kakao.jPanda.njb.dao;



import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.njb.domain.JoinDto;
import com.kakao.jPanda.njb.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor    //
public class MemberDao {
	
	private final SqlSession sqlSession;

	public void insertMember(JoinDto memberInfo) {
		
		sqlSession.insert("insertMember", memberInfo);
		sqlSession.insert("insertAccount", memberInfo);
	}

	public void insertMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}
