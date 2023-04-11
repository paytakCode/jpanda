package com.kakao.jPanda.bsm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TalentController {
	private final TalentService service;
	
	// 등록 페이지 
	@GetMapping("/talent")
	public String talentWrite(Model model) {
		List<Category> categoryList = service.categoryList();
		model.addAttribute("categoryList", categoryList);
		
		return "talentFrom";
	}
}
