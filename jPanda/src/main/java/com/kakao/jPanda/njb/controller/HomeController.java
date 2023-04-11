package com.kakao.jPanda.njb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.jPanda.njb.domain.Member;
import com.kakao.jPanda.njb.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
	public class HomeController {
		
		private final MemberService memberservice;
		
	    @GetMapping("/login")
	    public String login(Model model) {
	    	System.out.println("login start...");
	        return "njb/login";
	    }
	    @GetMapping("/join")
	    public String joinForm(Model model) {
	    	System.out.println("join Start...");
	 
	    	return "njb/join";
	    }
	    @PostMapping("/join")
	    public String join(Member member) {
	    	memberservice.joinMember(member);
	    	return "redirect:/join";
	    	
	    }
	    
	    
	}

