package com.kakao.jPanda.njb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.njb.domain.BankDto;
import com.kakao.jPanda.njb.domain.EmailVerifDto;
import com.kakao.jPanda.njb.domain.MemberDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor 
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private final SqlSession sqlSession;
	
	@Override
	public boolean insertMember(MemberDto memberInfo){
		
	    try {
	        sqlSession.insert("insertMember", memberInfo);
	        sqlSession.insert("insertAccount", memberInfo);
	        return true; // 삽입이 성공하면 true 반환
	    } catch (Exception e) {
	        return false; // 삽입 중 오류 발생 시 false 반환
	    }
	}



	@Override
	public List<BankDto> selectBankList() {
	    return sqlSession.selectList("selectBankList");
	}
	
	@Override
	public int checkId(String memberId) {
		
		return sqlSession.selectOne("checkmemberId", memberId);
	}



	@Override
	public String findPwByIdAndEmail(String memberId, String email) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("memberId", memberId);
        paramMap.put("email", email);
        return sqlSession.selectOne("findPwByIdAndEmail", paramMap);
    }


	@Override
	public String findIdByNameAndEmail(String name, String email) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);
		paramMap.put("email",email);
		return sqlSession.selectOne("findIdByNameAndEmail", paramMap);
		
	}



	@Override //리절트타입             파라미터타
	public MemberDto login(MemberDto memberDto) {
																	//파라미터타입 
		return sqlSession.selectOne("selectMemberByIdAndPassword", memberDto);    // 결과가 resulttype;
	
	}



	@Override
	public MemberDto selectMember(String memberId) {

		return sqlSession.selectOne("selectMemberById",memberId);
	
	}



	@Override
	public Object updatePasswordById(String memberId, String encryptedPassword) {

	    int result = sqlSession.update("updatePasswordById", new HashMap<String, String>() {{
	        put("memberId", memberId);
	        put("newPw", encryptedPassword);
	    }});
	    return result;	}



	@Override
	public boolean withdrawMemberById(String memberId, String encryptedPassword) {
		
		int result = sqlSession.update("updateMemberStatusByMemberId",memberId);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}



	@Override
	public void updateMemberInfo(MemberDto memberInfo) {

		sqlSession.update("updateMemberInfo",memberInfo);
		
	}



	@Override
	public void insertVerificationCode(EmailVerifDto emailVerifDto) {

		sqlSession.insert("insertVerificationCode",emailVerifDto);
	}



	@Override
	public EmailVerifDto selectEmailVerif(EmailVerifDto emailVerifDto) {
		EmailVerifDto selectEmailVerifDto = sqlSession.selectOne("selectEmailVerif",emailVerifDto);
		return selectEmailVerifDto;
	}






	

}
