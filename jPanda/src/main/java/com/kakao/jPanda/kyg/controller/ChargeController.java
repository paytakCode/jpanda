package com.kakao.jPanda.kyg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.service.ChargeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ChargeController {
	
	private final ChargeService chargeService;
	
	@Autowired
	public ChargeController(ChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
//	private final ChargeService chargeService;
	
	@GetMapping("/chargePage")
	public String chargePage() {
		return "kyg/chargePage";
	}
	
//	충전 성공시 페이지
	@GetMapping("/test")
	public String test() {
		return "kyg/test";		
	}
	
//	밤부 충전
	@PostMapping("/chargePage")
	public String charge(ChargeDto chargeDto, Model model) {
		
		log.info("ChargeContoller charge() Start...");
		System.out.println("ChargeController charge() Start...");
//		String chargeService = chargeService.insertCharge(chargeDto);
		
		int resultCharge = chargeService.insertCharge(chargeDto);
//		int resultCharge = 1;
		if(resultCharge > 0) {
			System.out.println("ChargeController charge() resultCharge 완료 -> redirect:test");
			return "redirect:test";
		} else {
			System.out.println("ChargeContoller charge() resultCharge 실패 -> kyg/chargePage");
			model.addAttribute("chargeFailMsg", "충전실패 다시 시도해주세요");
			return "kyg/chargePage";
		}
		
	}
	
//	쿠폰 중복 확인
//	시큐리티 에러
	/*
	@GetMapping("/chargePage")
	@ResponseBody
	public boolean couponNoDupchk(@RequestParam(value = "couponNo") String couponNo) {
		log.info("ChargeContoller couponNoDupchk() Start...");
		
		boolean isAvailableCoupon = chargeService.couponCheck(couponNo);
		log.info("ChargeContoller couponNoDupchk() isAvailableCoupon {}", isAvailableCoupon);
		
		
		return isAvailableCoupon;
	}
	
	*/
	
	
	
	
	
	
	
	
	/*
	쿠폰 적용가능 체크 로직
	@GetMapping("/chargeCheck")
	@ResponseBody
	public boolean chrgeCheck(@RequestParam(value = "couponNo") String couponNo){
		//쿠폰이 있으면 false
		//쿠폰이 없으면 true
		boolean result = chargeService.couponCheck(couponNo);
		return result;
	}
	*/
	
}
	


