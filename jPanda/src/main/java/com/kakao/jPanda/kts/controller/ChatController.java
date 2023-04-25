package com.kakao.jPanda.kts.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		session.setAttribute("memberId", "아무거나");
		return "kts/chatView";
	}
    
    @GetMapping("/chat-before")
    public String chatBeforeView() {
        return "kts/chatBefore";
    }
	
	@ResponseBody
	@GetMapping("/chats")
	public List<Chat> chatListByUserId(@RequestParam String userId){
		List<Chat> foundchatList = chatService.findChatListByUserId(userId);	
		return foundchatList;
	}
	
	@ResponseBody
	@PostMapping("/chat")
	public ResponseEntity<Integer> chatSave(@Valid @RequestBody Chat chat){
		Integer result = chatService.saveChat(chat);
		if(result == 1) {
			return ResponseEntity.accepted().body(result);
		} else {
			return ResponseEntity.internalServerError().body(result);
		}
	}
}
