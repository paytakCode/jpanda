package com.kakao.jPanda.chat.dao;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.chat.domain.ChatDto;
import com.kakao.jPanda.chat.domain.ChatPartnerDto;
import com.kakao.jPanda.chat.domain.ChatReportDto;

public interface ChatDao {

	int insertChat(ChatDto chatDto);

	List<ChatPartnerDto> selectPartnerListByMemberId(String memberId);

    List<ChatDto> selectChatListByMemberIdAndPartnerId(Map<String, String> memberIdAndPartnerIdMap);

    int updateChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap);

    int insertReportByChatReport(ChatReportDto chatReportDto);

}
