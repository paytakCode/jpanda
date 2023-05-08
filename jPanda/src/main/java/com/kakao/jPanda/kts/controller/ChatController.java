package com.kakao.jPanda.kts.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.ChatPartner;
import com.kakao.jPanda.kts.domain.ChatReport;
import com.kakao.jPanda.kts.service.ChatService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	private final ChatService chatService;
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
		this.chatService = chatService;
		this.simpMessagingTemplate = simpMessagingTemplate;
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
	public List<ChatPartner> partnerListByMemberId(@PathVariable String memberId){
		log.info("[partnerListByMemberId] {}의 채팅 상대 목록을 불러옵니다.", memberId);
		List<ChatPartner> foundPartnerList = chatService.findPartnerListByMemberId(memberId);
		return foundPartnerList;
	}
	
	@MessageMapping("/message")
	public void sendMessage(Chat chat) {
		log.info("[sendMessage] 메세지를 전송합니다. {}", chat.toString());
		simpMessagingTemplate.convertAndSend("/message/" + chat.getReceiverId(), chat);
	}
	
	@MessageMapping("/read")
    public void readMessage(Map<String, String> readerIdAndPartnerId) {
        log.info("[readMessage] {}에게 {}가 메세지를 읽었다고 알립니다.", readerIdAndPartnerId.get("readerId"), readerIdAndPartnerId.get("partnerId"));
        simpMessagingTemplate.convertAndSend("/read/" + readerIdAndPartnerId.get("partnerId"), readerIdAndPartnerId.get("readerId"));
    }
	
	@ResponseBody
	@PostMapping("/chat")
	public ResponseEntity<Chat> chatSave(@Valid @RequestBody Chat chat){
		log.info("[chatSave] 메세지를 DB에 저장합니다. {}", chat.toString());
		Integer result = chatService.saveChat(chat);
		if(result == 1) {
		    log.info("[chatSave] 저장 완료 {}", chat);
			return ResponseEntity.ok(chat);
		} else {
            log.info("[chatSave] 저장 실패 {}", chat);
			return ResponseEntity.internalServerError().body(chat);
		}
	}
	
	@ResponseBody
	@PatchMapping("/chats")
	public ResponseEntity<Integer> chatModify(@RequestBody Map<String, String> readerIdAndPartnerIdMap){
        log.info("[chatModify] {}가 {}에게 받은 메세지를 DB에 읽음으로 수정합니다.", readerIdAndPartnerIdMap.get("readerId"), readerIdAndPartnerIdMap.get("partnerId"));
        Integer result = chatService.modifyChatByReaderIdAndPartnerId(readerIdAndPartnerIdMap);
        if(result >= 1) {
            log.info("[chatModify] 수정 완료 - {}", result);
            return ResponseEntity.ok(result);
        } else {
            log.info("[chatModify] 수정 실패 - {}", result);
            return ResponseEntity.internalServerError().body(result);
        }
	}
	
	@ResponseBody
	@PostMapping("/chat/report")
	public ResponseEntity<ChatReport> reportSave(@RequestBody ChatReport chatReport){
	    log.info("[reportSave] 신고내역을 DB에 저장합니다. {}", chatReport.toString());
        int result = chatService.saveReport(chatReport);
        if(result == 1) {
            log.info("[reportSave] 저장 완료 {}", chatReport);
            return ResponseEntity.ok(chatReport);
        } else {
            log.info("[reportSave] 저장 실패 {}", chatReport);
            return ResponseEntity.internalServerError().body(chatReport);
        }
	}

}
