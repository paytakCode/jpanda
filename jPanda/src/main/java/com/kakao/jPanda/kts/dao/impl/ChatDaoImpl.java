package com.kakao.jPanda.kts.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.kts.dao.ChatDao;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.ChatMessage;
import com.kakao.jPanda.kts.domain.Partner;

@Repository
public class ChatDaoImpl implements ChatDao {
	
	private final SqlSession sqlSession;
	
	@Autowired
	public ChatDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ChatMessage> selectChatMessageListByMemberId(String memberId) {
	    List<ChatMessage> selectedChatMessageList = sqlSession.selectList("selectChatMessageListByMemberId", memberId);
		return selectedChatMessageList;
	}

	@Override
	public Integer insertChat(Chat chat) {
		int result = sqlSession.insert("insertChat", chat);
		return result;
	}

	@Override
	public List<Partner> selectPartnerListByMemberId(String memberId) {
		List<Partner> selectedPartnerList = sqlSession.selectList("selectPartnerListByMemberId", memberId);
		return selectedPartnerList;
	}
	
}
