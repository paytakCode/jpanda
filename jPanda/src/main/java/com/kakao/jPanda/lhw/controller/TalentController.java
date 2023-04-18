package com.kakao.jPanda.lhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.service.TalentService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/talent")
public class TalentController {

	private final TalentService talentService;
	
	@GetMapping("/{talentNo}")
	public String talentDetails(@PathVariable Long talentNo, Model model) {
		System.out.println("Controller talentDetails Start");
		Talent talent = talentService.findTalentByTalentNo(talentNo);
		model.addAttribute("talent", talent);
		System.out.println(talent);
		return "lhw/Talent";
	}
}
