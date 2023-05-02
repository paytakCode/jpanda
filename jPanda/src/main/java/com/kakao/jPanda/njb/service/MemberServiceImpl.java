package com.kakao.jPanda.njb.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.njb.dao.MemberDao;


import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao memberDao;

	
	@Override
	public List<BankDto> selectBankList() {
		
		return memberDao.selectBankList();
	}

	@Override
	public void joinMember(MemberDto memberInfo) {

	    String encryptedPassword = PasswordEncryptor.encrypt(memberInfo.getPassword()); // 비밀번호 암호화
	    memberInfo.setPassword(encryptedPassword); // 암호화된 비밀번호로 설정
		memberDao.insertMember(memberInfo);		
	}

	@Override
	public int checkId(String memberId) {
		
		return memberDao.checkId(memberId);
	}

	@Override
	public String findPwByIdAndEmail(String memberId, String email) {

		return memberDao.findPwByIdAndEmail(memberId, email);
	
	}

	@Override
	public String findIdByNameAndEmail(String name, String email) {
		
		return memberDao.findIdByNameAndEmail(name, email);
	
	}

	@Override
	public boolean login(MemberDto memberDto) {
		
	    String encryptedPassword = PasswordEncryptor.encrypt(memberDto.getPassword()); // 비밀번호 암호화
	    memberDto.setPassword(encryptedPassword);
		MemberDto loginMemberDto = memberDao.login(memberDto);
  
		System.out.println(loginMemberDto);
		if(loginMemberDto != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public MemberDto selectMember(String id) {

		
			return memberDao.selectMember(id);
		
	}

	@Override
	public void updatePasswordById(String memberId, String newPw) {

	    	String encryptedPassword = PasswordEncryptor.encrypt(newPw); // 비밀번호 암호화
			
	    	memberDao.updatePasswordById(memberId,encryptedPassword);
	}

	@Override
	public void withdrawal(String memberId, String password) {
	    String encryptedPassword = PasswordEncryptor.encrypt(password); // 비밀번호 암호화
	    memberDao.deleteMemberById(memberId,encryptedPassword);
	    
			
	}
	
}

