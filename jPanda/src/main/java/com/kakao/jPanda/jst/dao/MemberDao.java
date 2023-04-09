package com.kakao.jPanda.jst.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.jst.domain.BambooUseDto;
import com.kakao.jPanda.jst.domain.MemberJoinDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TalentRefundDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDao {
	
	private SqlSession sqlSession;
	
	@Autowired
	public MemberDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void insertMember(MemberJoinDto memberJoinDto) {
		log.info("MemberDao insertMember called");
		sqlSession.insert("insertMember", memberJoinDto);
	}
	
	public void insertTalent(TalentDto talentDto) {
		sqlSession.insert("insertTalent", talentDto);
	}
	
	public void insertBamboo(BambooUseDto bambooUseDto) {
		sqlSession.insert("insertBamboo", bambooUseDto);
	}
	
	public void insertTalentRefund(TalentRefundDto talentRefundDto) {
		sqlSession.insert("insertTalentRefund", talentRefundDto);
	}
	
	
}//end class
