package com.kakao.jPanda.kts.service;

import java.util.List;

import com.kakao.jPanda.kts.domain.Chat;

public interface ChatService {

	List<Chat> findChatListByUserId(String userId);

	Integer saveChat(Chat chat);

}
