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
		
		//WebSocket url 매핑
		log.info("[registerWebSocketHandlers] called");
		registry.addHandler(textWsHandler, "/chat-ws");
	}
	
}
