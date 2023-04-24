package com.kakao.jPanda.kts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kakao.jPanda.kts.handler.TextWsHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final TextWsHandler textWsHandler;
	
	@Autowired
	public WebSocketConfig(TextWsHandler textWsHandler) {
		log.info("[WebSocketConfig] initialized");
		this.textWsHandler = textWsHandler;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//      WebSocket을 사용할 수없는 경우 대체 전송을 사용할 수 있도록 SockJS 폴백 옵션을 활성화합니다.
//      SockJS 클라이언트는 "/ws"에 연결하여 사용 가능한 최상의 전송 (websocket, xhr-streaming, xhr-polling 등)을 시도
		log.info("[registerWebSocketHandlers] called");
		registry.addHandler(textWsHandler, "/ws"); //AllowedOrigins("*").withSockJS();
	}
	
}
