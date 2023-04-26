package com.kakao.jPanda.jst.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kakao.jPanda.jst.handler.ReplyEchoHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSocket
@Slf4j
public class TradeWebSocketConfig implements WebSocketConfigurer{
	
	private final ReplyEchoHandler replyEchoHandler;
	
	@Autowired
	public TradeWebSocketConfig(ReplyEchoHandler replyEchoHandler) {
		this.replyEchoHandler = replyEchoHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		log.info("registerWebSocketHandlers");
		registry.addHandler(replyEchoHandler, "/trade-ws");
	}

}//endClass
