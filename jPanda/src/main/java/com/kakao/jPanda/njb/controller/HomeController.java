package com.kakao.jPanda.njb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.jPanda.common.annotation.NoLoginCheck;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
	public class HomeController {

		@NoLoginCheck
	    @GetMapping("/login")
	    public String login(Model model) {
	    	System.out.println("login start...");
	        return "njb/login";
	    }
		@NoLoginCheck
	    @GetMapping("/find")
	    public String find(Model model) {
	    	System.out.println("find start....");
	    	return "njb/find";
	    }
	}

