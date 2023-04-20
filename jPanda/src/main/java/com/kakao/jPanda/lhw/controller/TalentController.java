package com.kakao.jPanda.lhw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.jPanda.lhw.domain.Review;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.service.ReviewService;
import com.kakao.jPanda.lhw.service.TalentService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/talent")
public class TalentController {

	private final TalentService talentService;
	private final ReviewService reviewService;
	
	@GetMapping("/{talentNo}")
	public String talentDetails(@PathVariable Long talentNo, Model model) {
		System.out.println("Controller talentDetails Start");
		Talent talent = talentService.findTalentByTalentNo(talentNo);
		List<Review> reviewList = reviewService.findReviewListByTalentNo(talentNo);
		model.addAttribute("talent", talent);
		model.addAttribute("reviewList", reviewList);
		return "lhw/Talent";
	}
	
	
}
