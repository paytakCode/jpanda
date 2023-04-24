package com.kakao.jPanda.kts.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	private final ChatService chatService;
//	private final String testId = "bgdtkgxqyj2";
	
	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@GetMapping("/chat")
	public String chatView(HttpSession session) {
		log.info("[chatView] session id : {}", session.getId());
		return "kts/chatView";
	}
	
	@ResponseBody
	@GetMapping("/chats")
	public List<Chat> chatListByUserId(@RequestParam String userId){
		List<Chat> foundchatList = chatService.findChatListByUserId(userId);
		return foundchatList;
	}
}
