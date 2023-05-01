package com.kakao.jPanda.lhw.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/talents")
public class TalentDetailController {

	private final TalentService talentService;
	
	// 재능 상세페이지 및 리뷰 리스트 불러오기
	@GetMapping("/talent/{talentNo}")
	public String talentDetails(@PathVariable Long talentNo, Model model, HttpSession session) {
		System.out.println("Controller talentDetails Start");
		Talent talent = talentService.findBoardTalentByTalentNo(talentNo);
		List<Review> reviewList = talentService.findReviewListByTalentNo(talentNo);
		
		String loginId = "";
		if(session.getAttribute("loginId") == null) {
			loginId = "guest";
		} else {
			loginId = (String) session.getAttribute("loginId");
		}
		
		model.addAttribute("loginId", loginId);
		model.addAttribute("talent", talent);
		model.addAttribute("reviewList", reviewList);
		return "lhw/Talent";
	}
	
	// 리뷰 인서트
	@ResponseBody
	@PostMapping("/talent/{talentNo}/reviews")
	public List<Review> reviewAdd(@PathVariable("talentNo") Long talentNo, @RequestBody Review review, HttpSession session, Model model) {
		System.out.println("Controller reviewAdd Start");
		review.setTalentNo(talentNo); // talentNo를 review 객체에 설정
		review.setReviewerId((String)session.getAttribute("loginId")); // reviewerId를 review 객체에 설정
		int reviewAdd = talentService.addReview(review);
		System.out.println("리뷰 추가 완료시 1-> " + reviewAdd);
		
		String loginId = "";
		if(session.getAttribute("loginId") == null) {
			loginId = "guest";
		} else {
			loginId = (String) session.getAttribute("loginId");
		}
		model.addAttribute("loginId", loginId);
		
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	// 리뷰 업데이트
	@ResponseBody
	@PutMapping("/talent/{talentNo}/reviews/{reviewNo}")
	public List<Review> reviewModify(@PathVariable("talentNo") Long talentNo, @PathVariable("reviewNo") Long reviewNo, 
							   @RequestBody Review review, HttpSession session, Model model) {
		System.out.println("Controller reviewModify Start");
		System.out.println("talentNo : " + talentNo);
		System.out.println("reviweNo : " + reviewNo);
		review.setReviewerId((String)session.getAttribute("loginId"));
	    int modifyReview = talentService.modifyReview(review);
	    System.out.println("리뷰 수정 완료시 1-> " + modifyReview);
	    
	    String loginId = "";
		if(session.getAttribute("loginId") == null) {
			loginId = "guest";
		} else {
			loginId = (String) session.getAttribute("loginId");
		}
		model.addAttribute("loginId", loginId);
	    
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	// 리뷰 딜리트
	@ResponseBody
	@DeleteMapping("/talent/{talentNo}/reviews/{reviewNo}")
	public List<Review> reviewRemove(@PathVariable("talentNo") Long talentNo, @PathVariable("reviewNo") Long reviewNo,
									@RequestBody Review review, HttpSession session) {
		System.out.println("Controller reviewRemove Start");
		int reviewRemove = talentService.removeReview(review);
		System.out.println("리뷰 삭제 완료시1-> " + reviewRemove);
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	
	
}
