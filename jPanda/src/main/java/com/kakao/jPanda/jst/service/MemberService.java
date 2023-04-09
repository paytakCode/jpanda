package com.kakao.jPanda.jst.service;

import com.kakao.jPanda.jst.domain.BambooUseDto;
import com.kakao.jPanda.jst.domain.MemberJoinDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TalentRefundDto;

public interface MemberService {
	void addMember(MemberJoinDto memberJoinDto);
	void addTalent(TalentDto talentDto);
	void addBamboo(BambooUseDto bambooUseDto);
	void addTalentRefund(TalentRefundDto talentRefundDto);
}
