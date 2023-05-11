package com.kakao.jPanda.chat.service;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.chat.domain.ChatDto;
import com.kakao.jPanda.chat.domain.ChatPartnerDto;
import com.kakao.jPanda.chat.domain.ChatReportDto;

public interface ChatService {

	int saveChat(ChatDto chatDto);

	List<ChatPartnerDto> findPartnerListByMemberId(String memberId);

    List<ChatDto> findChatListByMemberIdAndPartnerId(String memberId, String partnerId);

    int modifyChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap);

    int saveReport(ChatReportDto chatReportDto);

}
