package com.kakao.jPanda.kts.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kakao.jPanda.kts.dao.ChatDao;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.Partner;
import com.kakao.jPanda.kts.service.ChatService;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
	
	private final ChatDao chatDao;

	public ChatServiceImpl(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
	
	@Override
	public List<Chat> findChatListByMemberId(String memberId) {
		List<Chat> selectedChatList = chatDao.selectChatListByMemberId(memberId);
		return selectedChatList;
	}

	@Override
	public Integer saveChat(Chat chat) {
		Integer result = chatDao.insertChat(chat);
		return result;
	}

	@Override
	public List<Partner> findPartnerListByMemberId(String memberId) {
		List<Partner> selectedPartnerList = chatDao.selectPartnerListByMemberId(memberId);
		return selectedPartnerList;
	}

}
