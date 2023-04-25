package com.kakao.jPanda.kts.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.kts.dao.ChatDao;
import com.kakao.jPanda.kts.domain.Chat;

@Repository
public class ChatDaoImpl implements ChatDao {
	
	private final SqlSession sqlSession;
	
	@Autowired
	public ChatDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Chat> selectChatListByUserId(String userId) {
	    List<Chat> selectedChatList = null;
	    if (userId == null || userId == "") {
            selectedChatList = sqlSession.selectList("selectChatList");
	    } else {
	        selectedChatList = sqlSession.selectList("selectChatListById", userId);
	    }
		return selectedChatList;
	}

	@Override
	public Integer insertChat(Chat chat) {
		int result = sqlSession.insert("insertChat", chat);
		return result;
	}
	
}
