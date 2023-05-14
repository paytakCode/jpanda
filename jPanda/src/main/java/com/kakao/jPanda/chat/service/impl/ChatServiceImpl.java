package com.kakao.jPanda.chat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.chat.dao.ChatDao;
import com.kakao.jPanda.chat.domain.ChatDto;
import com.kakao.jPanda.chat.domain.ChatPartnerDto;
import com.kakao.jPanda.chat.domain.ChatReportDto;
import com.kakao.jPanda.chat.service.ChatService;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
	
	private final ChatDao chatDao;

	public ChatServiceImpl(ChatDao chatDao) {
		this.chatDao = chatDao;
	}
	
	@Override
	public List<ChatDto> findChatListByMemberIdAndPartnerId(String memberId, String partnerId) {
	    Map<String, String> memberIdAndPartnerMap = new HashMap<String, String>();
	    memberIdAndPartnerMap.put("memberId", memberId);
	    memberIdAndPartnerMap.put("partnerId", partnerId);
	    List<ChatDto> selectedChatList = chatDao.selectChatListByMemberIdAndPartnerId(memberIdAndPartnerMap);
		return selectedChatList;
	}

	@Override
	public int saveChat(ChatDto chatDto) {
	    chatDto.setRead('F');
	    int result = chatDao.insertChat(chatDto);
		return result;
	}

	@Override
	public List<ChatPartnerDto> findPartnerListByMemberId(String memberId) {
		List<ChatPartnerDto> selectedPartnerList = chatDao.selectPartnerListByMemberId(memberId);
		return selectedPartnerList;
	}

    @Override
    public int modifyChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap) {
        int result = chatDao.updateChatByReaderIdAndPartnerId(readerIdAndPartnerIdMap);
        return result;
    }

    @Override
    public int saveReport(ChatReportDto chatReportDto) {
        int result = chatDao.insertReportByChatReport(chatReportDto);
        return result;
    }

}
