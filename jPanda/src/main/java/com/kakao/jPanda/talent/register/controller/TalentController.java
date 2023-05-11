package com.kakao.jPanda.talent.register.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.kakao.jPanda.talent.register.domain.CategoryDto;
import com.kakao.jPanda.talent.register.domain.TalentDto;
import com.kakao.jPanda.talent.register.service.RegistTalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/talent")	
public class TalentController {
	private final RegistTalentService registTalentService;
	
	// 재능 등록 페이지 이동
	@GetMapping("/write-form")
	public String talentWriteForm(Model model) {
		return "talent/register/talentWriteForm";
	}
	
	// 카테고리 불러오기
	@ResponseBody
	@GetMapping("/categorys")
	public List<CategoryDto> findCategoryList(){
		return registTalentService.findCategorys();
	}
	// 재능 DB Insert
	@ResponseBody
	@PostMapping("/talent")
	public String talentAdd(TalentDto talent, HttpSession session) {
		return registTalentService.addTalent(talent,session);
	}
	
	// 재능 DB Update
	@ResponseBody
	@PutMapping("/talent")
	public String talentModify(TalentDto talent, HttpSession session) {
		return registTalentService.modifyTalent(talent, session);
	}
	
	// 이미지 서버 저장 후 상대 경로 반환
	@ResponseBody
	@PostMapping("/image-upload")
	public ModelAndView talentImageUpload(MultipartHttpServletRequest request) {
		ModelAndView modelAndView = registTalentService.talentImageUpload(request);
		
		return modelAndView;
	}
	
	// 수정 페이지 이동
	@GetMapping("/talents/{talentNo}/update-form") // /talents/{talentNo}/update-form
	public String talentUpdateFrom(@PathVariable Long talentNo, Model model, HttpSession session) { // @PathVariable
		TalentDto talent = registTalentService.findTalentByTalentNo(talentNo);
		model.addAttribute("talent", talent);
		return "talent/register/talentUpdateForm";
	}
	
}
