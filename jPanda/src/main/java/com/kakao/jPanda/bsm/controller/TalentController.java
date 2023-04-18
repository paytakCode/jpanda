package com.kakao.jPanda.bsm.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;
import com.kakao.jPanda.bsm.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/talent")
public class TalentController {
	private final TalentService service;
	
	// Test Main 페이지 이동
	@GetMapping("/")
	public String talentTest() {
		return "bsm/talentTest";
	}
	
	// 재능 등록 페이지 이동
	@GetMapping("/write-form")
	public String talenWritetForm(Model model) {
		List<Category> categoryList = service.findCategorys();
		model.addAttribute("categoryList", categoryList);
		
		return "bsm/talentWriteForm";
	}
	
	// 재능 DB Insert
	@PostMapping("/talent")
	public String talentAdd(Talent talent) {
		service.addTalent(talent);
		return "bsm/talentTest";
	}
	
	// 재능 수정 Update
	@PutMapping("/talent")
	public String talentModify(Talent talent) {
		service.modifyTalent(talent);
		return "bsm/talentTest";
	}
	
	// 상세정보 이미지 서버 저장 후 상대 경로 반환
	@PostMapping("/image-upload")
	public ModelAndView talentImageUpload(MultipartHttpServletRequest request) {
		ModelAndView modelAndView = service.talentImageUpload(request);
		
		return modelAndView;
	}
	
	// 수정 페이지 이동
	@GetMapping("/update-form")
	public String talentUpdateFrom(Long talentNo, Model model) {
		// dto 새로 만들 것
		System.out.println(talentNo);
		Talent talent = service.findTalent(talentNo);
		List<Category> categoryList = service.findCategorys();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("talent", talent);
		return "bsm/talentUpdateForm";
	}
	
}
