package com.kakao.jPanda.chat.dao.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.chat.dao.ChatDao;
import com.kakao.jPanda.chat.domain.ChatDto;
import com.kakao.jPanda.chat.domain.ChatPartnerDto;
import com.kakao.jPanda.chat.domain.ChatReportDto;

@Repository
public class ChatDaoImpl implements ChatDao {
	
	private final SqlSession sqlSession;
	
	@Autowired
	public ChatDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertChat(ChatDto chatDto) {
		int result = sqlSession.insert("insertChat", chatDto);
		return result;
	}

	@Override
	public List<ChatPartnerDto> selectPartnerListByMemberId(String memberId) {
		List<ChatPartnerDto> selectedPartnerList = sqlSession.selectList("selectPartnerListByMemberId", memberId);
		return selectedPartnerList;
	}

    @Override
    public List<ChatDto> selectChatListByMemberIdAndPartnerId(
            Map<String, String> memberIdAndPartnerIdMap) {
        List<ChatDto> selectedChatList = sqlSession.selectList("selectChatListByMemberIdAndPartnerId", memberIdAndPartnerIdMap);
        return selectedChatList;
    }

    @Override
    public int updateChatByReaderIdAndPartnerId(Map<String, String> readerIdAndPartnerIdMap) {
        int result = sqlSession.update("updateChatByReaderIdAndPartnerId", readerIdAndPartnerIdMap);
        return result;
    }

    @Override
    public int insertReportByChatReport(ChatReportDto chatReportDto) {
        int result = sqlSession.insert("insertReportByChatReport", chatReportDto);
        return result;
    }
	
}
