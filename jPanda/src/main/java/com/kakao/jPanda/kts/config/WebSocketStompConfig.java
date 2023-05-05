package com.kakao.jPanda.kts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import com.kakao.jPanda.kts.handler.StompHandShakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
	
    private final StompHandShakeInterceptor stompHandShakeInterceptor;
    
    @Autowired
    public WebSocketStompConfig(StompHandShakeInterceptor stompHandShakeInterceptor) {
        this.stompHandShakeInterceptor = stompHandShakeInterceptor;
    }
    
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp/chat")
				.setAllowedOriginPatterns("http://localhost:8888")
				.addInterceptors(stompHandShakeInterceptor)
				.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/chat"); // 
		registry.enableSimpleBroker("/direct");
	}
	
	
}
