package com.kakao.jPanda.kts.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TextWsHandler extends TextWebSocketHandler{
	
	//Connection 이후 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("[afterConnectionEstablished] session id : {}", session.getId());
	}
	
	//Message 수신시 실행되는 메서드
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		log.info("[handleMessage] session id : {}, message : {}", session.getId(), message);
	}
	
	//Transprot 중 Error 발생 시 실행되는 메서드
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.error("[handleTransportError] session id : {}, error Info : {}", session.getId(), exception.getMessage());
	}
	
	//Connection Close 시 실행되는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("[afterConnectionClosed] session id : {}, status : {}", session.getId(), status);
	}
}
