package com.kakao.jPanda.kts.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.jPanda.kts.domain.Chat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TextWsHandler extends TextWebSocketHandler{
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	
	//Connection 이후 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String memberId = (String)session.getUri().getQuery();
		log.info("[afterConnectionEstablished] WebSocket Connected - member id : {}, session id : {}", memberId, session.getId());
		
		if(sessionMap.get(memberId) == null) {
	        sessionMap.put(memberId, session);
		} else {
	        sessionMap.replace(memberId, session);
		}
		
	}
	
	//Message 수신시 실행되는 메서드
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		Chat chat = objectMapper.readValue((String)message.getPayload(), Chat.class);
		log.info("[handleMessage] Message Received - session id : {}, chat : {}", session.getId(), chat.toString());
		WebSocketSession receiver = sessionMap.get(chat.getReceiverId());
		receiver.sendMessage(message);
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
		sessionMap.values().remove(session);
		sessionMap.keySet().forEach(k -> {
		    log.info("member Id : {}, session id : {}", k, sessionMap.get(k));
		    });
	}
}
