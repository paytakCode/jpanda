package com.kakao.jPanda.lhw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@PostMapping("/talents/{talentNo}/reviews")
	public List<Review> reviewAdd(@PathVariable("talentNo") Long talentNo, @RequestBody Review review) {
		System.out.println("Controller reviewAdd Start");
		review.setTalentNo(talentNo); // talentNo를 review 객체에 설정
		int reviewAdd = talentService.addReview(review);
		System.out.println("리뷰 추가 완료시 1-> " + reviewAdd);
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	@ResponseBody
	@PutMapping("/talents/{talentNo}/reviews/{reviewNo}")
	public List<Review> reviewModify(@PathVariable("talentNo") Long talentNo, @PathVariable("reviewNo") Long reviewNo, 
							   @RequestBody Review review) {
		System.out.println("Controller reviewModify Start");
		System.out.println("talentNo : " + talentNo);
		System.out.println("reviweNo : " + reviewNo);
	    int modifyReview = talentService.modifyReview(review);
	    System.out.println("리뷰 수정 완료시 1-> " + modifyReview);
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	@ResponseBody
	@DeleteMapping("/talents/{talentNo}/reviews/{reviewNo}")
	public List<Review> reviewRemove(@PathVariable("talentNo") Long talentNo, @PathVariable("reviewNo") Long reviewNo,
									@RequestBody Review review) {
		System.out.println("Controller reviewRemove Start");
		int reviewRemove = talentService.removeReview(review);
		System.out.println("리뷰 삭제 완료시1-> " + reviewRemove);
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	
	
	
}
