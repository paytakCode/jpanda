package com.kakao.jPanda.jst.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.jst.dao.MemberDao;
import com.kakao.jPanda.jst.domain.BambooUseDto;
import com.kakao.jPanda.jst.domain.MemberJoinDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TalentRefundDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public void addMember(MemberJoinDto memberJoinDto) {
		log.info("MSI addMember called");
		memberDao.insertMember(memberJoinDto);
		
	}

	@Override
	public void addTalent(TalentDto talentDto) {
		memberDao.insertTalent(talentDto);
	}

	@Override
	public void addBamboo(BambooUseDto bambooUseDto) {
		// TODO Auto-generated method stub
		memberDao.insertBamboo(bambooUseDto);
	}

	@Override
	public void addTalentRefund(TalentRefundDto talentRefundDto) {
		// TODO Auto-generated method stub
		memberDao.insertTalentRefund(talentRefundDto);
		
	}

}
