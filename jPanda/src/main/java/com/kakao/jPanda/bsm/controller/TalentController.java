package com.kakao.jPanda.bsm.controller;

import java.util.List;
import java.util.Map;

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

import com.kakao.jPanda.bsm.domain.CategoryDto;
import com.kakao.jPanda.bsm.domain.NoticeDto;
import com.kakao.jPanda.bsm.domain.PagerDto;
import com.kakao.jPanda.bsm.domain.TalentDto;
import com.kakao.jPanda.bsm.service.NoticeService;
import com.kakao.jPanda.bsm.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/talent")	
public class TalentController {
	private final TalentService talentService;
	private final NoticeService noticeService;

	// Test Main 페이지 이동
	@GetMapping("/")
	public String talentTest(Model model, HttpSession session) {
		model = talentService.findMainPageTalents(model);
		
		return "bsm/talentTestMainpage";
	}
	
	// 재능 등록 페이지 이동
	@GetMapping("/write-form")
	public String talenWritetForm(Model model, HttpSession session) {
		List<CategoryDto> categoryList = talentService.findCategorys();
		model.addAttribute("categoryList", categoryList);
		
		return "bsm/talentWriteForm";
	}
	
	// 재능 DB Insert
	@ResponseBody
	@PostMapping("/talent")
	public String talentAdd(TalentDto talent) {
		String result = talentService.addTalent(talent);
		return result;
	}
	
	// 재능 수정 Update
	@ResponseBody
	@PutMapping("/talent")
	public String talentModify(TalentDto talent) {
		String result = talentService.modifyTalent(talent);
		return result;
	}
	
	// 이미지 서버 저장 후 상대 경로 반환
	@ResponseBody
	@PostMapping("/image-upload")
	public ModelAndView talentImageUpload(MultipartHttpServletRequest request) {
		ModelAndView modelAndView = talentService.talentImageUpload(request);
		
		return modelAndView;
	}
	
	// 수정 페이지 이동
	@GetMapping("/talents/{talentNo}/update-form") // /talents/{talentNo}/update-form
	public String talentUpdateFrom(@PathVariable Long talentNo, Model model, HttpSession session) { // @PathVariable
		System.out.println(talentNo);
		TalentDto talent = talentService.findTalentByTalentNo(talentNo);
		List<CategoryDto> categoryList = talentService.findCategorys();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("talent", talent);
		
		return "bsm/talentUpdateForm";
	}	
	
	// 공지사항 페이지 이동
	@GetMapping("/notice") 
	public String noticePage() {
		return "bsm/notice";
	}
	
	// 공지사항 불러오기
	@ResponseBody
	@GetMapping("/notice/notices")
	public Map<String, Object> noticeListBySearchAndCurrentPage(PagerDto pager) {
		Map<String, Object> map = noticeService.findNoticeCountByPager(pager);
		
		return map;
	}
	
	// 공지사항 세부 페이지
	@GetMapping("/notice/{noticeNo}/detail")
	public String noticeDetailByNoticeNo(@PathVariable Long noticeNo, Model model) {
		NoticeDto notice = noticeService.findNoticeByNoticeNo(noticeNo);
		model.addAttribute("notice", notice);
		return "bsm/noticeDetail";
	}
	
}
