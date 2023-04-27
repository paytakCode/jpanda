package com.kakao.jPanda.kts.dao;

import java.util.List;

import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.ChatMessage;
import com.kakao.jPanda.kts.domain.Partner;

public interface ChatDao {

	Integer insertChat(Chat chat);

	List<ChatMessage> selectChatMessageListByMemberId(String memberId);

	List<Partner> selectPartnerListByMemberId(String memberId);

}
