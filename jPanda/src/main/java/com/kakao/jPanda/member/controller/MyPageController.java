package com.kakao.jPanda.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.jPanda.member.domain.MemberDto;
import com.kakao.jPanda.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageController {
	
	private final MemberService memberservice;

    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        log.info("[myPage] memberId {}",memberId);
        if (memberId != null) {
            // 로그인이 되어있는 경우
            MemberDto memberInfo = memberservice.findMember(memberId);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("editMode", false);
            return "mypage/mypage";
        }
        //로그인 안되어있으면 로그인페이지로 이동
        return "login/login";
    }
    
    @PostMapping("/updateMember")
    public String editMemberInfo(@ModelAttribute("memberInfo") MemberDto memberInfo, HttpSession session) {
    	
    	session.setAttribute("memberInfo", memberInfo);
    	log.info("[editMemberInfo] memberInfo {}", memberInfo);
    	memberservice.editMemberInfo(memberInfo);
        return "redirect:/mypage";
    }
    
    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(HttpSession session, @RequestParam String password) {
        String memberId = (String) session.getAttribute("memberId");
        boolean isWithdrawalSuccessful = memberservice.withdrawal(memberId, password);

        if (isWithdrawalSuccessful) {
            session.invalidate();
            return ResponseEntity.ok("탈되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호를 확인해주세요.");
        }
    }
}
