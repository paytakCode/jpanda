package com.kakao.jPanda.njb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
	public class HomeController {

		
	    @GetMapping("/login")
	    public String login(Model model) {
	    	System.out.println("login start...");
	        return "njb/login";
	    }
	    
	    @GetMapping("/find")
	    public String find(Model model) {
	    	System.out.println("find start....");
	    	return "njb/find";
	    }
	    @GetMapping("/mypage")
	    public String myPage(Model model) {
	    	return "njb/mypage";
	    }
	}

