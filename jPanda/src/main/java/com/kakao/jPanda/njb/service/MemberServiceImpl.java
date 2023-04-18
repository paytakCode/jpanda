package com.kakao.jPanda.njb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.njb.dao.BankDao;
import com.kakao.jPanda.njb.dao.MemberDao;
import com.kakao.jPanda.njb.domain.Bank;
import com.kakao.jPanda.njb.domain.JoinDto;
import com.kakao.jPanda.njb.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao md;
	private final BankDao bd;
	@Override
	public void joinMember(Member member) {
		
		md.insertMember(member);

	}

	@Override
	public List<Bank> getBankList() {
		
		return bd.getBankList();
	}

	@Override
	public void joinMember(JoinDto memberInfo) {
//		memberInfo.setBirth(memberInfo.getYear() + "/" + memberInfo.getMonth() + "/" + memberInfo.getDay());
		md.insertMember(memberInfo);		
	}


	
}
