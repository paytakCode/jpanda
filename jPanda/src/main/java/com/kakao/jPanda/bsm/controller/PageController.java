package com.kakao.jPanda.bsm.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.jPanda.bsm.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	private final TalentService talentService;
	
	// Test Main 페이지 이동
	@GetMapping("/main")
	public String talentTest(Model model, HttpSession session) {
		model = talentService.findMainPageTalents(model);
		
		return "bsm/talentTestMainpage";
	}
	
}
