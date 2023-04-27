package com.kakao.jPanda.kts.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.ChatMessage;
import com.kakao.jPanda.kts.domain.Member;
import com.kakao.jPanda.kts.domain.Partner;
import com.kakao.jPanda.kts.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	private final SqlSession sqlsession; // 임시
	private final ChatService chatService;
	
	@Autowired
	public ChatController(ChatService chatService, SqlSession sqlsession) {
		this.chatService = chatService;
		this.sqlsession = sqlsession;
	}
	
	@GetMapping("/chat-stomp")
	public String chatStompView(HttpSession session) {
		log.info("[chatStompView] session id : {}", session.getId());
		return "kts/chatStompView";
	}
	
	@ResponseBody
	@GetMapping("/chats/{memberId}/chatMessages")
	public List<ChatMessage> chatMessageListByMemberId(@PathVariable String memberId){
		log.info("[chatMessageListByMemberId]");
		List<ChatMessage> foundChatMessageList = chatService.findChatMessageListByMemberId(memberId);	
		return foundChatMessageList;
	}
	
	@ResponseBody
	@GetMapping("/chats/{memberId}/partners")
	public List<Partner> partnerListByMemberId(@PathVariable String memberId){
		log.info("[partnerListByMemberId]");
		List<Partner> foundPartnerList = chatService.findPartnerListByMemberId(memberId);
		return foundPartnerList;
	}
	
	@MessageMapping("/message")
	@SendToUser(value = "/direct/{memberId}", broadcast = false)
	public ChatMessage sendMessage(ChatMessage message) {
		log.info("[sendMessage] MessageInfo {}", message.toString());
		return message;
	}
	
	@ResponseBody
	@PostMapping("/chat")
	public ResponseEntity<Integer> chatSave(@Valid @RequestBody Chat chat){
		log.info("[chatSave]");
		Integer result = chatService.saveChat(chat);
		if(result == 1) {
			return ResponseEntity.accepted().body(result);
		} else {
			return ResponseEntity.internalServerError().body(result);
		}
	}
	
	//테스트용 추후 삭제 예정
	@ResponseBody
	@GetMapping("/members")
	public List<Member> memberList(){
		log.info("[memberList]");
		List<Member> memberList = sqlsession.selectList("selectMemberList");
		return memberList;
	}
}
