package com.kakao.jPanda.lhw.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final BoardService boardService;
	
	@RequestMapping("/BoardMain")
	public String mainTalentBoard(Model model) {
		System.out.println("BoardMain Controller Start");
		List<Talent> talentList = boardService.getTalentList();
		List<Notice> noticeList = boardService.getNoticeList();
		model.addAttribute("talentList", talentList);
		model.addAttribute("noticeList", noticeList);
		return "lhw/BoardSell";
	}
	
	
}
