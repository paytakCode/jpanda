package com.kakao.jPanda.kts.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.Partner;
import com.kakao.jPanda.kts.service.ChatService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	private final ChatService chatService;
	
	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}
    
    @ResponseBody
    @GetMapping("/chats/{memberId}")
    public List<Chat> chatListByMemberIdAndPartnerId(@PathVariable String memberId, @RequestParam(name = "partner-id") String partnerId){
        log.info("[chatListByMemberIdAndPartnerId] {}의 상대 {}와의 채팅 정보를 불러옵니다.", memberId, partnerId);
        List<Chat> foundChatList = chatService.findChatListByMemberIdAndPartnerId(memberId, partnerId);    
        return foundChatList;
    }
	
	@ResponseBody
	@GetMapping("/chats/{memberId}/partners")
	public List<Partner> partnerListByMemberId(@PathVariable String memberId){
		log.info("[partnerListByMemberId] {}의 채팅 상대 목록을 불러옵니다.", memberId);
		List<Partner> foundPartnerList = chatService.findPartnerListByMemberId(memberId);
		return foundPartnerList;
	}
	
	@MessageMapping("/message")
	@SendToUser(value = "/direct/{memberId}", broadcast = false)
	public Chat sendMessage(Chat chat) {
		log.info("[sendMessage] 메세지를 전송합니다. {}", chat.toString());
		return chat;
	}
	
	@ResponseBody
	@PostMapping("/chat")
	public ResponseEntity<Integer> chatSave(@Valid @RequestBody Chat chat){
		log.info("[chatSave] 메세지를 DB에 저장합니다. {}", chat.toString());
		Integer result = chatService.saveChat(chat);
		if(result == 1) {
		    log.info("[chatSave] 저장 완료 {}", result);
			return ResponseEntity.accepted().body(result);
		} else {
            log.info("[chatSave] 저장 실패 {}", result);
			return ResponseEntity.internalServerError().body(result);
		}
	}
	
	//default Layout Test용
	@GetMapping("/default-layout")
	public String chatLayout() {
	    return "/common/default";
	}
}
