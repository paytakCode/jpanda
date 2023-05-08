package com.kakao.jPanda.njb.dao;

import java.util.List;

import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.EmailVerifDto;
import com.kakao.jPanda.njb.domain.MemberDto;

public interface MemberDao {

	public void insertMember(MemberDto memberInfo);

	public List<BankDto> selectBankList();
	
	public int checkId(String memberId);

	String findIdByNameAndEmail(String name, String email);
	
	String findPwByIdAndEmail(String memberId,String email);

	public MemberDto login(MemberDto memberDto);

	public MemberDto selectMember(String memberId);

	public Object updatePasswordById(String memberId, String encryptedPassword);

	public boolean withdrawMemberById(String memberId, String encryptedPassword);

	public void updateMemberInfo(MemberDto memberInfo);

	public void insertVerificationCode(EmailVerifDto emailVerifDto);

	public EmailVerifDto selectEmailVerif(EmailVerifDto emailVerifDto);


	
}	
