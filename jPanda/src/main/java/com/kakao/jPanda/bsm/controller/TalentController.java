package com.kakao.jPanda.bsm.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String talentTest(Model model) {
		List<Talent> bestSellerTalentList = service.findBestSellerTalents();
		List<Talent> topRatedTalentList = service.findTopRatedTalents();
		List<Talent> newTrendTalentList = service.findNewTrendTalents();
		List<Talent> randomTalentList = service.findRandomTalents();
		
		model.addAttribute("bestSellerTalent", bestSellerTalentList);
		model.addAttribute("topRatedTalent", topRatedTalentList);
		model.addAttribute("newTrendTalent", newTrendTalentList);
		model.addAttribute("randomTalent", randomTalentList);
		return "bsm/talentTestMainpage";
	}
	
	// 재능 등록 페이지 이동
	@GetMapping("/write-form")
	public String talenWritetForm(Model model) {
		List<Category> categoryList = service.findCategorys();
		model.addAttribute("categoryList", categoryList);
		
		return "bsm/talentWriteForm";
	}
	
	// 재능 DB Insert
	@ResponseBody
	@PostMapping("/talent")
	public String talentAdd(Talent talent) {
		String result = service.addTalent(talent);
		return result;
	}
	
	// 재능 수정 Update
	@ResponseBody
	@PutMapping("/talent")
	public String talentModify(Talent talent) {
		String result = service.modifyTalent(talent);
		return result;
	}
	
	// 이미지 서버 저장 후 상대 경로 반환
	@PostMapping("/image-upload")
	public ModelAndView talentImageUpload(MultipartHttpServletRequest request) {
		ModelAndView modelAndView = service.talentImageUpload(request);
		
		return modelAndView;
	}
	
	// 수정 페이지 이동
	@GetMapping("/talents/{talentNo}/update-form") // /talents/{talentNo}/update-form
	public String talentUpdateFrom(@PathVariable Long talentNo, Model model) { // @PathVariable
		System.out.println(talentNo);
		Talent talent = service.findTalentByTalentNo(talentNo);
		List<Category> categoryList = service.findCategorys();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("talent", talent);
		return "bsm/talentUpdateForm";
	}	
	
}
