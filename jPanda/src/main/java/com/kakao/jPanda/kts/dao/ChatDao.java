package com.kakao.jPanda.kts.dao;

import java.util.List;
import java.util.Map;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.Partner;

public interface ChatDao {

	int insertChat(Chat chat);

	List<Partner> selectPartnerListByMemberId(String memberId);

    List<Chat> selectChatListByMemberIdAndPartnerId(Map<String, String> memberIdAndPartnerMap);

    int updateChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap);

}
