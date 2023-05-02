package com.kakao.jPanda.kts.dao;

import java.util.List;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.Partner;

public interface ChatDao {

	Integer insertChat(Chat chat);

	List<Chat> selectChatListByMemberId(String memberId);

	List<Partner> selectPartnerListByMemberId(String memberId);

}
