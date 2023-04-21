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
import com.kakao.jPanda.lhw.service.CategoryService;
import com.kakao.jPanda.lhw.service.FiltersService;
import com.kakao.jPanda.lhw.service.NoticeService;
import com.kakao.jPanda.lhw.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final TalentService talentService;
	private final CategoryService categoryService;
	private final NoticeService noticeService;
	private final FiltersService filtersService;
	
	@GetMapping("/")
	public String boardList(Model model) {
		System.out.println("Controller boardList Start");
		List<Talent> talentList = talentService.findTalentList();
		List<Notice> noticeList = noticeService.findNoticeList();
		List<Category> upperCategoryList = categoryService.findUpperCategoryList();
		model.addAttribute("talentList", talentList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("upperCategoryList", upperCategoryList);
		return "lhw/Board";
	}
	
	
	@ResponseBody
	@GetMapping("/update-talent-list-by-upper-category-no")
	public List<Talent> talentListByUpperCategoryNo(Long upperCategoryNo) {
		System.out.println("Controller talentListByUpperCategoryNo Start");
		List<Talent> talentList = talentService.findTalentListByUpperCategoryNo(upperCategoryNo);
		return talentList;
	}
	
	
	@ResponseBody
	@GetMapping("/update-talent-list-by-lower-category-no")
	public List<Talent> talentListByLowerCategoryNo(Long lowerCategoryNo){
		System.out.println("Controller talentListByLowerCategoryNo Start");
		List<Talent> talentList = talentService.findTalentListByLowerCategoryNo(lowerCategoryNo);
		return talentList;
	}
	
	//중분류 카테고리 리스트
	@ResponseBody
	@GetMapping("/update-lower-category-list-by-upper-category-no") // /categorys?upper-category-no="{upperCategoryNo}"
	public List<Category> lowerCategoryListByUpperCategoryNo(Long upperCategoryNo) {
		System.out.println("Controller lowerCategoryListByUpperCategoryNo Start");
		List<Category> lowerCategotyList = categoryService.findLowerCategoryListByUpperCategoryNo(upperCategoryNo);
		return lowerCategotyList;
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/talents?upper-category-and-filters={filters}") public
	 * List<Talent> talentListByFilters(@RequestParam Filters filters){ List<Talent>
	 * talentListByFilters =
	 * filtersService.findTalentListByUpperCategoryAndFilter(filters); return
	 * talentListByFilters; }
	 */
	
	
}
