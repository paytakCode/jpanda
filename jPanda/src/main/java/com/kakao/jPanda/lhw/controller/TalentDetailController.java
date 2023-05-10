package com.kakao.jPanda.lhw.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.common.annotation.NoLoginCheck;
import com.kakao.jPanda.kyg.service.ChargeService;
import com.kakao.jPanda.lhw.domain.BambooUseDto;
import com.kakao.jPanda.lhw.domain.ReportDto;
import com.kakao.jPanda.lhw.domain.ReviewDto;
import com.kakao.jPanda.lhw.domain.TalentDto;
import com.kakao.jPanda.lhw.service.TalentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/talents")
public class TalentDetailController {

	private final TalentService talentService;
	private final ChargeService chargeService;
	
	
	// 재능 상세페이지 및 리뷰 리스트 불러오기
	@NoLoginCheck
	@GetMapping("/talent/{talentNo}")  //s
	public String talentDetails(@PathVariable Long talentNo, Model model, HttpSession session) {
		log.info("Controller talentDetails Start");
		// 조회수 업데이트
		talentService.modifyTalentToViewCount(talentNo);
		// 상세 페이지 탤런트 정보
		TalentDto talent = talentService.findBoardTalentByTalentNo(talentNo);
		// 리뷰 리스트 
		List<ReviewDto> reviewList = talentService.findReviewListByTalentNo(talentNo);
		// 상세 페이지에서 memberId 검증 필요
		String memberId = (String)session.getAttribute("memberId");

		model.addAttribute("memberId", memberId);
		model.addAttribute("talent", talent);
		model.addAttribute("reviewList", reviewList);
		
		return "lhw/talent";
	}
	
	
	// 리뷰 인서트
	@ResponseBody
	@PostMapping("/talent/{talentNo}/review") //s
	public int reviewAdd(@PathVariable("talentNo") Long talentNo, @RequestBody ReviewDto review) {
		log.info("Controller reviewAdd Start");
		log.info("review : " + review);
	    // 리뷰 구매 검증 완료 후 인서트
	    int result = talentService.addReview(review);
	    log.info("Review Insert Success 1-> "+ result );
	    
	    return result;
	}
	
	
	// 리뷰 업데이트
	@ResponseBody
	@PutMapping("/talent/{talentNo}/reviews/{reviewNo}") //s
	public List<ReviewDto> reviewModify(@PathVariable("talentNo") Long talentNo, @PathVariable("reviewNo") Long reviewNo, 
							   @RequestBody ReviewDto review, HttpSession session) {
		log.info("Controller reviewModify Start");
		
	    int modifyReview = talentService.modifyReview(review);
	    log.info("Review Update Success 1-> "+ modifyReview );
	    
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	
	// 리뷰 딜리트
	@ResponseBody
	@DeleteMapping("/talent/{talentNo}/reviews/{reviewNo}") //s
	public List<ReviewDto> reviewRemove(@PathVariable("talentNo") Long talentNo, @PathVariable("reviewNo") Long reviewNo,
									@RequestBody ReviewDto review, HttpSession session) {
		System.out.println("Controller reviewRemove Start");
		int reviewRemove = talentService.removeReview(review);
		log.info("Review Delset Success 1-> "+ reviewRemove);
		return talentService.findReviewListByTalentNo(talentNo);
	}
	
	
	// 게시글 삭제 버튼 눌렀을때 게시글 상태 판매 종료로 전환
	@GetMapping("/talent/updateStatus/{talentNo}")  //talents/{talentNo}/status 게시글 삭제가 아닌 판매 종료로 변경 및 put 매핑으로 변경 , 
	public String talentStatusUpdate(@PathVariable Long talentNo) {
		talentService.updateTalentStatus(talentNo);
		return "redirect:/board";
	}
	
	
	// 구매하기 버튼 인서트              
	@ResponseBody
	@PostMapping("/bambooUse") // /BambooUse
	public int purchaseAdd(@RequestBody BambooUseDto bambooUse, HttpSession session) {
		System.out.println("Controller purchaseAdd Start");
		// memberId 변수에 세션안에 있는 memberId 저장
		String memberId = (String)session.getAttribute("memberId");
		
		// bambooUse 객체 안에 memberId 저장 
		bambooUse.setBuyerId(memberId);
		System.out.println("구매 회원 정보 -> " + bambooUse);
		
		// 해당하는 memberId의 totalBamboo를 저장
		Long totalBamboo = chargeService.findTotalBambooByMemberId(memberId);
		System.out.println("totalBamboo -> " + totalBamboo);
		
		// 구매자 정보 검증 후에 인서트
		int bambooUseInsert = talentService.addBambooUse(bambooUse, totalBamboo);
		System.out.println("구매 완료시1 -> " + bambooUseInsert);

		return bambooUseInsert;
	}
	
	
	// 신고하기 
	@ResponseBody
	@PostMapping("/talent/report/{talentNo}")  // /talents/{talentNo}/report  
	public String reportAdd(@PathVariable Long talentNo, @RequestParam("reportId")String reportId, @RequestParam("blackId")String blackId, 
					     @RequestParam("issue")String issue, @RequestParam("reportDate") Timestamp reportDate) {
		System.out.println("Controller reportAdd Start");
		ReportDto report = new ReportDto();
		report.setBlackId(blackId);
		report.setReportId(reportId);
		report.setIssue(issue);
		report.setReportDate(reportDate);
		report.setTalentNo(talentNo);
		System.out.println("신고 정보 -> " + report);
		
		String result = talentService.addReport(report);
		System.out.println("신고 최종 결과 -> " + result);
		
		return result;
	}
	

	
}
