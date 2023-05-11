package com.kakao.jPanda.bsm.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.bsm.domain.TalentDto;
import com.kakao.jPanda.bsm.service.TalentService;
import com.kakao.jPanda.common.annotation.NoLoginCheck;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	private final TalentService talentService;
	
	// 메인 페이지 이동
	@NoLoginCheck
	@GetMapping("/main")
	public String main(Model model) {
		model.addAttribute("bestSellerTalent", talentService.findBestSellerTalents());
		model.addAttribute("topRatedTalent", talentService.findTopRatedTalents());
		model.addAttribute("newTrendTalent", talentService.findNewTrendTalents());
		model.addAttribute("randomTalent", talentService.findRandomTalents());
		
		return "bsm/main";
	}
	
}
