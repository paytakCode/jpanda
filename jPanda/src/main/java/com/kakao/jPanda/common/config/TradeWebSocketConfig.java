package com.kakao.jPanda.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kakao.jPanda.trade.handler.TradeWebSocketHandler;
import com.kakao.jPanda.trade.handler.TradeWebSocketInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSocket
@Slf4j
public class TradeWebSocketConfig implements WebSocketConfigurer{
	
	private final TradeWebSocketHandler tradeWebSocketHandler;
	private final TradeWebSocketInterceptor tradeWebSocketInterceptor;
	
	@Autowired
	public TradeWebSocketConfig(TradeWebSocketHandler tradeWebSocketHandler, TradeWebSocketInterceptor tradeWebSocketInterceptor) {
		this.tradeWebSocketHandler = tradeWebSocketHandler;
		this.tradeWebSocketInterceptor = tradeWebSocketInterceptor;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		log.info("registerWebSocketHandlers");
		registry.addHandler(tradeWebSocketHandler, "/trade-ws").addInterceptors(tradeWebSocketInterceptor);
	}

}//endClass
