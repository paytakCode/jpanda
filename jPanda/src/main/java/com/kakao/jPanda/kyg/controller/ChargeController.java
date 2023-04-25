package com.kakao.jPanda.kyg.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.service.ChargeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/charge")		//홈 http://localhost:8888/charge/
public class ChargeController {
	
	private final ChargeService chargeService;
	
	@Autowired
	public ChargeController(ChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
//	private final ChargeService chargeService;
	
//	충전 메인페이지
	@GetMapping("/")
	public String chargePage() {
		return "kyg/chargePage";
	}
	

//	충전 성공시 페이지로 이동
	@GetMapping("/test")
	public String test() {
		return "kyg/test";		
	}
	
	
	
//	밤부 충전
	@ResponseBody
	@PostMapping("/charge") // /charge로 수정하기
	public Map<String, String> chargeAdd(@RequestBody ChargeDto chargeDto, Model model) {
		
		log.info("ChargeContoller charge() Start...");
		//System.out.println("ChargeController charge() Start...");
		
		int resultCharge = chargeService.addCharge(chargeDto);
		Map<String, String> resultMap = new HashMap<>();
		
		
		if(resultCharge > 0) {
			//System.out.println("ChargeController charge() resultCharge 완료 -> redirect:test");
			log.info("ChargeController charge() resultCharge 완료");
			 resultMap.put("result", "success");
			return resultMap;
		// ajax에서 거르지 못한 잘못된 충전 출력
		} else {
			//System.out.println("ChargeContoller charge() resultCharge 실패 -> kyg/chargePage");
			log.error("ChargeContoller charge() resultCharge 실패");
			resultMap.put("result", "fail");
			model.addAttribute("chargeFailMsg", "충전실패 다시 시도해주세요");
			return resultMap;
		}
		
	}
	
	
	// 원본
//	@PostMapping("/charge") // /charge로 수정하기
//	public String chargeAdd(ChargeDto chargeDto, Model model) {
//		
//		log.info("ChargeContoller charge() Start...");
//		System.out.println("ChargeController charge() Start...");
//		
//		int resultCharge = chargeService.addCharge(chargeDto);
//		
//		if(resultCharge > 0) {
//			System.out.println("ChargeController charge() resultCharge 완료 -> redirect:test");
//			return "redirect:test";
//		// ajax에서 거르지 못한 잘못된 충전 출력
//		} else {
//			System.out.println("ChargeContoller charge() resultCharge 실패 -> kyg/chargePage");
//			model.addAttribute("chargeFailMsg", "충전실패 다시 시도해주세요");
//			return "kyg/chargePage";
//		}
//		
//	}
	
	
//	이용 가능한 쿠폰 체크
	@GetMapping(value = "/check-available-coupon")
	@ResponseBody
	public CouponUseDto checkAvailableCoupon(CouponUseDto couponUseDto) {
		String resultStr = "0";
		CouponUseDto couponUseDto3 = new CouponUseDto();	// 쿠폰사용유무 결과값을 dto에 담아서 보냄
		log.info("ChargeContoller couponDetails() Start...");
		log.info("ChargeContoller checkAvailableCoupon couponUseDto.toString() -> {}", couponUseDto.toString());
		
		// 사용 결과만 가져옴 -> 사용 가능한 쿠폰, 사용 했던 쿠폰을 비교하여, 회원이 사용했던 이력이 있는 쿠폰의 결과를 가져와 사용가능 여부를 따짐
		int resultInt = chargeService.checkAvailableCoupon(couponUseDto);
		
		// 충전 금액과 쿠폰의 금액을 차감해 실제 충전에 사용되는 금액을 구하기 위해 쿠폰의 금액을 가져옴
		// 쿠폰의 금액을 가져옴
		Long couponValue = chargeService.getAvailAmountCoupon(couponUseDto);
		
		couponUseDto3.setResult(resultInt);
		couponUseDto3.setCouponValue(couponValue);
		
		//System.out.println("Controller checkAvailableCoupon resultInt->"+resultInt);
		//System.out.println("Controller checkAvailableCoupon couponValue->"+couponValue);
		log.info("Controller checkAvailableCoupon resultInt-> {}", resultInt);
		log.info("Controller checkAvailableCoupon couponValue-> {}", couponValue);
		
		return couponUseDto3;
	}
	
	@GetMapping(path = "/members/{memberId}/total-bamboo")
	@ResponseBody
	public String  totalBamboo(@PathVariable String memberId) {
		log.info("Controller totalBamboo Start...");
		log.info("Controller totalBamboo memberId -> {}", memberId);
		//int foundTotalBamboo = chargeService.findTotalBamboo(memberId);
		long foundTotalBamboo = chargeService.findTotalBamboo(memberId);
		//String  foundTotalBambooStr =  Integer.toString(foundTotalBamboo);
		String  foundTotalBambooStr =  Long.toString(foundTotalBamboo);
		
		log.info("Controller totalBamboo calculatedTotalBamboo -> {}", foundTotalBamboo);
		
		return foundTotalBambooStr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @GetMapping(value = "/check-available-coupon")
	 * 
	 * @ResponseBody public Map<String, Integer> checkAvailableCoupon(CouponUseDto
	 * couponUseDto) { log.info("ChargeContoller couponDetails() Start...");
	 * log.info(couponUseDto.toString()); Integer result =
	 * chargeService.checkAvailableCoupon(couponUseDto); Map<String, Integer> map =
	 * new HashMap<String, Integer>(); map.put("result", result); return map; }
	 * 
	 */
	
	// GetMapping pathvariable 생각하고 검색하기
	
	
	
//	가지고있는 밤부의 총 합계
	
//	@GetMapping(value = "/members/#{memberId}/total-bamboo")
//	@ResponseBody
//	public int totalBamboo(@PathVariable String memberId) {
//			log.info("ChargeController totalBamboo() Start...");
//			
//			int calculateTotalBamboo = chargeService.calculateTotalBamboo(memberId);
//		
//			log.info("ChargeController totalBamboo() calculateTotalBamboo {} -> ", calculateTotalBamboo);
//		return calculateTotalBamboo;
//	}
	
	
}
	


