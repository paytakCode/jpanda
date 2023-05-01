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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kakao.jPanda.kts.domain.Chat;
import com.kakao.jPanda.kts.domain.Member;
import com.kakao.jPanda.kts.domain.Partner;
import com.kakao.jPanda.kts.service.ChatService;
import com.kakao.jPanda.njb.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	private final SqlSession sqlsession; // 임시
	private final ChatService chatService;
	private final MemberService memberservice;
	
	@Autowired
	public ChatController(ChatService chatService, SqlSession sqlsession, MemberService memberservice) {
		this.chatService = chatService;
		this.sqlsession = sqlsession;
		this.memberservice = memberservice;
	}
	
	@GetMapping("/chat-test")
	public String chatTestView(HttpSession session) {
		log.info("[chatTestView] session id : {}", session.getId());
		return "kts/chatTestView";
	}
	
	@ResponseBody
	@GetMapping("/chats")
	public List<Chat> chatListByMemberId(@RequestParam String memberId){
		log.info("[chatListByMemberId]");
		List<Chat> foundChatList = chatService.findChatListByMemberId(memberId);	
		return foundChatList;
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
	public Chat sendMessage(Chat chat) {
		log.info("[sendMessage] chatInfo {}", chat.toString());
		return chat;
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
	
	//모든 계정 아이디와 비밀번호 동일하게 변경하는 임시 메소드
	@ResponseBody
	@GetMapping("/members-init-pwd")
	public String memberPasswordInit() {
	    List<Member> memberList = sqlsession.selectList("selectMemberList");
	    for(Member member : memberList) {
	        memberservice.updatePasswordById(member.getMemberId(), member.getMemberId());
	    }
	    return "End";
	}
	
	//chat-layout Test용
	@GetMapping("/chat-layout")
	public String chatLayout() {
	    return "/common/chat-sample";
	}
}
