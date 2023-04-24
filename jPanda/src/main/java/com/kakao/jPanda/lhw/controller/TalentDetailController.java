package com.kakao.jPanda.lhw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.lhw.domain.Review;
import com.kakao.jPanda.lhw.domain.Talent;
import com.kakao.jPanda.lhw.service.TalentService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/talent")
public class TalentDetailController {

	private final TalentService talentService;
	
	@GetMapping("/talents/{talentNo}")
	public String talentDetails(@PathVariable Long talentNo, Model model) {
		System.out.println("Controller talentDetails Start");
		Talent talent = talentService.findTalentByTalentNo(talentNo);
		List<Review> reviewList = talentService.findReviewListByTalentNo(talentNo);
		model.addAttribute("talent", talent);
		model.addAttribute("reviewList", reviewList);
		return "lhw/Talent";
	}
	
	@ResponseBody
	@PostMapping("/talents/{talentNo}/review")
	public List<Review> reviewAdd(@PathVariable("talentNo") Long talentNo, @RequestBody Review review) {
		System.out.println("Controller reviewAdd Start");
		review.setTalentNo(talentNo); // talentNo를 review 객체에 설정
		int reviewAdd = talentService.insertReview(review);
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
}
