package com.kakao.jPanda.lhw.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.domain.TalentForBoard;
import com.kakao.jPanda.lhw.service.TalentService;
import com.kakao.jPanda.lhw.service.TalentViewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TalentController {
	
	private final TalentService boardService;
	private final TalentViewService talentViewService;
	
	@RequestMapping("/TalentBoard")
	public String mainTalentBoard(Model model) {
		System.out.println("Controller TalentBoard Start");
		List<TalentForBoard> talentList = boardService.getTalentList();
		List<Notice> noticeList = boardService.getNoticeList();
		List<Category> upperCategory = boardService.getCategory();
		model.addAttribute("talentList", talentList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("getCategoryNo", upperCategory);
		return "lhw/TalentBoard";
	}
	
	@GetMapping("/TalentView")
	public String TalentBoardView(Long talentNo, Model model) {
		System.out.println("Controller TalentBoardView Start");
		Talent talentView = talentViewService.getTalentView(talentNo);
		model.addAttribute("getTalentView", talentView);
		System.out.println(talentView);
		return "lhw/TalentView";
	}
	
	@ResponseBody
	@GetMapping("/getAjaxUpperList")
	public List<Talent> getAjaxUpperList(Long upperCategoryNo, Model model) {
		System.out.println("upperCategoryNo " + upperCategoryNo);
		System.out.println("Controller getAjaxUpperList Start");
		List<Talent> getAjaxUpperList = boardService.getUpperCategoryList(upperCategoryNo);
		System.out.println(" getAjaxUpperList " +  getAjaxUpperList.size());
		return getAjaxUpperList;
	}
}
