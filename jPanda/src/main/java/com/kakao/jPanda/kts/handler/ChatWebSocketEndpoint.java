package com.kakao.jPanda.kts.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.kakao.jPanda.kts.domain.Chat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ServerEndpoint("/ws2")
public class ChatWebSocketEndpoint {
	
		private final Map<String, Session> sessionMap = new HashMap<String, Session>();
	
		@OnMessage
	    public void onMessage(Session session, Chat chat) {
		   log.info("[handleMessage] Message Received - session id : {}, chat : {}", session.getId(), chat.toString());
		   try {
			session.getBasicRemote().sendObject(chat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    
	    @OnOpen
	    public void onOpen(Session session) {
	        // 클라이언트 연결 시 처리할 로직 작성
	    }
	    
	    @OnClose
	    public void onClose(Session session) {
	        // 클라이언트 연결 종료 시 처리할 로직 작성
	    }
	    
}
