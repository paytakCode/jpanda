package com.kakao.jPanda.lhw.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/")
	public String boardList(Model model) {
		System.out.println("Controller boardList Start");
		List<Talent> talentList = boardService.findTalentList();
		List<Notice> noticeList = boardService.findNoticeList();
		List<Category> upperCategoryList = boardService.findUpperCategoryList();
		model.addAttribute("talentList", talentList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("upperCategoryList", upperCategoryList);
		return "lhw/Board";
	}
	
	// 대분류 카테고리를 눌렀을때 리스트
	@ResponseBody
	@GetMapping("/update-talent-list-by-upper-category-no")
	public List<Talent> talentListByUpperCategoryNo(Long upperCategoryNo) {
		System.out.println("Controller talentListByUpperCategoryNo Start");
		List<Talent> talentList = boardService.findTalentListByUpperCategoryNo(upperCategoryNo);
		return talentList;
	}
	
	
	// 중분류 카테고리를 불러오는 리스트
	@ResponseBody
	@GetMapping("/update-lower-category-list-by-upper-category-no") // /categorys?upper-category-no="{upperCategoryNo}"
	public List<Category> lowerCategoryListByUpperCategoryNo(Long upperCategoryNo) {
		System.out.println("Controller lowerCategoryListByUpperCategoryNo Start");
		List<Category> lowerCategotyList = boardService.findLowerCategoryListByUpperCategoryNo(upperCategoryNo);
		return lowerCategotyList;
	}
	
	// 중분류 카테고리 눌렀을때 리스트
	@ResponseBody
	@GetMapping("/update-talent-list-by-lower-category-no")
	public List<Talent> talentListByLowerCategoryNo(Long lowerCategoryNo){
		System.out.println("Controller talentListByLowerCategoryNo Start");
		List<Talent> talentList = boardService.findTalentListByLowerCategoryNo(lowerCategoryNo);
		return talentList;
	}
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/talents?upper-category-and-filters={filters}") public
	 * List<Talent> talentListByFilters(@RequestParam Filters filters){
	 * List<Talent> talentListByFilters =
	 * boardService.findTalentListByUpperCategoryAndFilter(filters); 
	 * return talentListByFilters; 
	 * }
	 */
	
	
}
