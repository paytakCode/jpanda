package com.kakao.jPanda.njb.dao;

import java.util.List;

import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.MemberDto;

public interface MemberDao {

	public void insertMember(MemberDto memberInfo);

	public List<BankDto> selectBankList();
	
	public int checkId(String id);

	String findIdByNameAndEmail(String name, String email);
	
	String findPwByIdAndEmail(String id,String email);

	public MemberDto login(MemberDto memberDto);

	public MemberDto selectMember(String id);

	public Object updatePasswordById(String id, String encryptedPassword);

	public void deleteMemberById(String loginId, String encryptedPassword);

	
}	
