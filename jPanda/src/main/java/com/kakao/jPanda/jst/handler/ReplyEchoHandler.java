package com.kakao.jPanda.jst.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.jPanda.jst.domain.TradeDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ReplyEchoHandler extends TextWebSocketHandler{
	
	//Session 관리를 위한 Map
	private final Map<String, WebSocketSession> sessionMap = new HashMap<>();
	
	//ObjectWrapper
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String memberId = session.getUri().getQuery();
		log.info("afterConnectionEstablished memberId : " +  memberId);
		sessionMap.put(memberId, session);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		TradeDto tradeDto = objectMapper.readValue((String)message.getPayload(), TradeDto.class);
	    log.info("memberid : {}, talentNo : {}", tradeDto.getMemberId(), tradeDto.getTalentNo());
		
	    sessionMap.get(tradeDto.getMemberId()).sendMessage(message);
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
	
	
}
