package com.kakao.jPanda.kts.service;

import java.util.List;
import java.util.Map;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.Partner;

public interface ChatService {

	int saveChat(Chat chat);

	List<Partner> findPartnerListByMemberId(String memberId);

    List<Chat> findChatListByMemberIdAndPartnerId(String memberId, String partnerId);

    int modifyChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap);

}
