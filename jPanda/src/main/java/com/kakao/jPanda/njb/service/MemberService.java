package com.kakao.jPanda.njb.service;


import java.util.List;

import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.MemberDto;


public interface MemberService {

	List<BankDto>selectBankList(); //find 로 바꾸기  
	void joinMember(MemberDto memberInfo);
	int checkId(String memberId);
	String findPwByIdAndEmail(String memberId, String email);
	String findIdByNameAndEmail(String name, String email);
	boolean login(MemberDto memberDto);
	MemberDto selectMember(String id);
	void updatePasswordById(String memberId, String newPw);
	void withdrawal(String memberId, String password);

}
