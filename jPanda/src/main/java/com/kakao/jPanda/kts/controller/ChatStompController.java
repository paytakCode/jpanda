package com.kakao.jPanda.kts.controller;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.jPanda.kts.domain.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatStompController {
	
	@GetMapping("/chat-stomp")
	public String chatStompView(HttpSession session) {
		log.info("[chatStompView] session id : {}", session.getId());
		return "kts/chatStompView";
	}
	
	@MessageMapping("/message")
	@SendToUser(value = "/direct/{memberId}", broadcast = false)
	public ChatMessage sendMessageToMember(ChatMessage message) {
		return message;
	}
	
	
	@MessageMapping("/hello")
	@SendToUser(value = "/queue/reply", broadcast = false)
	public String handleHello(String message, @Header("simpSessionId") String sessionId) {
	    return "Hello, " + message + " (session " + sessionId + ")";
	}

}
