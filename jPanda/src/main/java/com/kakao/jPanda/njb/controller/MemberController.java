package com.kakao.jPanda.njb.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.jPanda.njb.domain.Bank;
import com.kakao.jPanda.njb.domain.JoinDto;
import com.kakao.jPanda.njb.domain.Member;
import com.kakao.jPanda.njb.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
	
	private final MemberService memberservice;
	
    @GetMapping("/join")
    public String joinForm(Model model) {
    	System.out.println("join Start...");
 	    List<Bank> bankList = memberservice.getBankList();
 	    System.out.println(bankList.size());
 	    model.addAttribute("bankList", bankList);
    	return "njb/join";
    }
    @PostMapping("/join")
    public String join(JoinDto memberInfo) {
    	System.out.println("PostJoin start...");
    	System.out.println(memberInfo.getCode());
    	memberservice.joinMember(memberInfo);
    	return "redirect:/join";
    	
    }
    
    
}
