package com.kakao.jPanda.lhw.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.lhw.domain.Category;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("")
	public String boardView(Model model) {
		System.out.println("Controller boardList Start");
		List<Notice> noticeList = boardService.findNoticeList();
		List<Category> upperCategoryList = boardService.findUpperCategoryList();
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("upperCategoryList", upperCategoryList);
		return "lhw/Board";
	}
	
	
	// 중분류 카테고리를 불러오는 리스트
	@ResponseBody
	@GetMapping("/categorys/lower-category-nos") 
	public List<Category> lowerCategoryListByUpperCategoryNo(@RequestParam Long upperCategoryNo) {
		System.out.println("Controller lowerCategoryListByUpperCategoryNo Start");
		List<Category> lowerCategotyList = boardService.findLowerCategoryListByUpperCategoryNo(upperCategoryNo);
		return lowerCategotyList;
	}
	
	
	//재능 리스트 불러오기 (필터 통합)
	@ResponseBody
	@GetMapping("/talents-test")
	public List<Talent> talentListTest(Filters filters){
		System.out.println(filters.toString());
		List<Talent> talentListByFilters = boardService.findTalentListByFilter(filters);
		/*
		 * for(int i = 0; i <talentListByFilters.size(); i++) {
		 * System.out.println(talentListByFilters.get(i).toString()); }
		 */
		return talentListByFilters;
	}
	
	
}
