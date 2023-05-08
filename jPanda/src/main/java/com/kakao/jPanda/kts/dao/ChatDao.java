package com.kakao.jPanda.kts.dao;

import java.util.List;
import java.util.Map;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.ChatReport;
import com.kakao.jPanda.kts.domain.ChatPartner;

public interface ChatDao {

	int insertChat(Chat chat);

	List<ChatPartner> selectPartnerListByMemberId(String memberId);

    List<Chat> selectChatListByMemberIdAndPartnerId(Map<String, String> memberIdAndPartnerIdMap);

    int updateChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap);

    int insertReportByChatReport(ChatReport chatReport);

}
