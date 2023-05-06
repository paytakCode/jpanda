package com.kakao.jPanda.kts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public List<Chat> findChatListByMemberIdAndPartnerId(String memberId, String partnerId) {
	    Map<String, String> memberIdAndPartnerMap = new HashMap<String, String>();
	    memberIdAndPartnerMap.put("memberId", memberId);
	    memberIdAndPartnerMap.put("partnerId", partnerId);
	    List<Chat> selectedChatList = chatDao.selectChatListByMemberIdAndPartnerId(memberIdAndPartnerMap);
		return selectedChatList;
	}

	@Override
	public int saveChat(Chat chat) {
	    chat.setRead('F');
	    int result = chatDao.insertChat(chat);
		return result;
	}

	@Override
	public List<Partner> findPartnerListByMemberId(String memberId) {
		List<Partner> selectedPartnerList = chatDao.selectPartnerListByMemberId(memberId);
		return selectedPartnerList;
	}

    @Override
    public int modifyChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap) {
        int result = chatDao.updateChatByReaderIdAndPartnerId(readerIdAndPartnerIdMap);
        return result;
    }

}
