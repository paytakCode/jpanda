package com.kakao.jPanda.lhw.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.lhw.domain.CategoryDto;
import com.kakao.jPanda.lhw.domain.FiltersDto;
import com.kakao.jPanda.lhw.domain.TalentDto;
import com.kakao.jPanda.lhw.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	// 공지사항 리스트와 대분류 카테고리 리스트 불러오기
	@GetMapping("")
	public String boardView(Model model, HttpSession session) {
		System.out.println("Controller boardList Start");
		List<CategoryDto> upperCategoryList = boardService.findUpperCategoryList();
		
		String memberId = (String) session.getAttribute("memberId");
		
		model.addAttribute("memberId", memberId);
		model.addAttribute("upperCategoryList", upperCategoryList);
		
		return "lhw/Board";
	}
	
	
	// 중분류 카테고리를 불러오는 리스트
	@ResponseBody
	@GetMapping("/categorys/lower-category-nos") 
	public List<CategoryDto> lowerCategoryListByUpperCategoryNo(@RequestParam Long upperCategoryNo) {
		System.out.println("Controller lowerCategoryListByUpperCategoryNo Start");
		List<CategoryDto> lowerCategotyList = boardService.findLowerCategoryListByUpperCategoryNo(upperCategoryNo);
		return lowerCategotyList;
	}
	
	
	//재능 리스트 불러오기 (필터 통합)
	@ResponseBody
	@GetMapping("/talents-list")
	public List<TalentDto> talentListTest(FiltersDto filters){
		System.out.println(filters.toString());
		List<TalentDto> talentListByFilters = boardService.findTalentListByFilter(filters);
		/*
		 * for(int i = 0; i <talentListByFilters.size(); i++) {
		 * System.out.println(talentListByFilters.get(i).toString()); }
		 */
		return talentListByFilters;
	}
	
	
}
