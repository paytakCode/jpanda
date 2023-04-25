package com.kakao.jPanda.kts.dao;

import java.util.List;

import com.kakao.jPanda.kts.domain.Chat;

public interface ChatDao {

	List<Chat> selectChatListByUserId(String userId);

	Integer insertChat(Chat chat);

}
