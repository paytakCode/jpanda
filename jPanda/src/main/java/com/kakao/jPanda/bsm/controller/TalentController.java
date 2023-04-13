package com.kakao.jPanda.bsm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;
import com.kakao.jPanda.bsm.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TalentController {
	private final TalentService service;
	
	// Test 페이지
	@GetMapping("/")
	public String talentTest() {
		return "bsm/talentTest";
	}
	
	// 재능 등록 페이지 이동
	@GetMapping("/talent")
	public String talentFrom(Model model) {
		List<Category> categoryList = service.categoryList();
		model.addAttribute("categoryList", categoryList);
		
		return "bsm/talentFrom";
	}
	
	// 재능 DB Insert
	@PostMapping("/talent")
	public String talentWrite(Talent talent, Model model) {
		service.talentWrite(talent);
		return "bsm/talentTest";
	}
	
	// 상세정보 이미지 서버 저장 후 상대 경로 반환
	@PostMapping(value = "/contentImage/upload")
	public ModelAndView contentImageUpload(MultipartHttpServletRequest request) {
		ModelAndView modelAndView = service.contentImageUpload(request);
		
		return modelAndView;
	}
	
	// 대표 이미지 서버 저장 후 상대 경로 반환
	@PostMapping("/mainImage/upload")
	@ResponseBody
	public ModelAndView uploadImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request) throws Exception {
		 ModelAndView modelAndView = service.mainImageUpload(file, request);
	 	
		return modelAndView;
	}
	 
	@GetMapping("/talentUpdate")
	public String talentUpdateFrom(int talentNo, Model model) {
		// dto 새로 만들 것
		System.out.println(talentNo);
		Talent talent = service.getTalent(talentNo);
		List<Category> categoryList = service.categoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("talent", talent);
		return "bsm/talentUpdate";
	}
	
	@PutMapping("/talent")
	public String talentUpdate(Talent talent) {
		service.talentUpdate(talent);
		return "bsm/talentTest";
	}
}
