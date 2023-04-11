package com.kakao.jPanda.njb.service;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.njb.dao.MemberDao;
import com.kakao.jPanda.njb.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao md;
	
	@Override
	public void joinMember(Member member) {
		
		md.insertMember(member);
		

	}

}
