package com.kakao.jPanda.bsm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// 등록 페이지 이동
	@GetMapping("/talent")
	public String talentFrom(Model model) {
		List<Category> categoryList = service.categoryList();
		model.addAttribute("categoryList", categoryList);
		
		return "bsm/talentFrom";
	}
	
	// 재능 등록
	@PostMapping("/talentUp")
	public String talentUpload(Talent talent, Model model) {
		service.talentUpload(talent);
		
		return "bsm/talentFrom";
	}
	
	@PostMapping(value = "/contentImage/upload")
	public ModelAndView contentImageUpload(MultipartHttpServletRequest request) {
		ModelAndView modelAndView = service.contentImageUpload(request);
		
		return modelAndView;
	}
	
	 @PostMapping("/mainImage/upload")
	 @ResponseBody
	 public ModelAndView uploadImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request) throws Exception {
		 ModelAndView modelAndView = service.mainImageUpload(file, request);
	 	
		 return modelAndView;
	    }
}
