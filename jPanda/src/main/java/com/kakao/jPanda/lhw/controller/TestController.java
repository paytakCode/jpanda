package com.kakao.jPanda.lhw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.jPanda.lhw.dao.TestTalentDao;
import com.kakao.jPanda.lhw.domain.TestTalent;
import com.kakao.jPanda.lhw.service.TestTalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/panda")
public class TestController {
	private final TestTalentService testTalentService;
	@RequestMapping("/testBoard")
	public String TestBoard (Model model) {
		List<TestTalent> testTalentList = testTalentService.findTestTalentList();
		model.addAttribute("testTalentList", testTalentList);
		
		return "lhw/Talent";
	}
}
