package com.kakao.jPanda.njb.service;


import java.util.List;

import com.kakao.jPanda.njb.domain.Bank;
import com.kakao.jPanda.njb.domain.JoinDto;
import com.kakao.jPanda.njb.domain.Member;

public interface MemberService {

	void joinMember(Member member);
	List<Bank> getBankList();
	void joinMember(JoinDto memberInfo);

}
