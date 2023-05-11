package com.kakao.jPanda.kyg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.kyg.domain.ChargeDto;
import com.kakao.jPanda.kyg.domain.CouponUseDto;
import com.kakao.jPanda.kyg.domain.PaymentDto;
import com.kakao.jPanda.kyg.service.ChargeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/charge")		
public class ChargeController {
	
	private final ChargeService chargeService;
	
	@Autowired
	public ChargeController(ChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
	/*
	 * 메인페이지
	 * 결제수단, RATIO를 테이블에 List형식으로 나타냄
	 * Model	TB payment -> method, bonusRatio 
	 * @param	Session, chargeDto, model
	 * @return	kyg/charge
	 */
	@GetMapping(value = "")
	public String charge(HttpSession session, Model model) {
		log.info("ChargeContoller charge() Start...");
		
		String chargerId = (String) session.getAttribute("memberId");
		log.info("ChargeContoller charge() chargerId -> {}", chargerId);
		
		int totalChargeCnt = chargeService.totalChargeCntByChargerId(chargerId);
		log.info("ChargeContoller charge() totalChargeCntByChargerId -> {}", totalChargeCnt);
		
		// 충전수단
		List<PaymentDto> getPaymentList = chargeService.findPaymentList();
		log.info("ChargeContoller charge() getPaymentList.size() -> {}", getPaymentList.size());
		
		model.addAttribute("listPayment", getPaymentList);
		 
		return "kyg/charge";
	}
	
	/*
	 * 밤부 충전
	 * charge.html에서 ajax 요청 처리
	 * 전달된 data를 ChargeDto에 저장 후 DB에 삽입
	 * @param	chargeDto, session
	 * @return	resultMap	/	resultMap을 return하여 callback시 success, fail에 따라 resultMap.put()을 console에 출력 
	 */
	@ResponseBody
	@PostMapping("/chargeAdd") 
	public Map<String, String> chargeAdd(@RequestBody ChargeDto chargeDto, HttpSession session) {
		String chargerId = (String) session.getAttribute("memberId");
		log.info("ChargeContoller chargeAdd() Start...");
		log.info("ChargeContoller chargeAdd() chargerId -> {}", chargerId);
		chargeDto.setChargerId(chargerId);
		log.info("ChargeContoller chargeAdd() couponUseDto.toString() -> {}", chargeDto.toString());
		
		int resultCharge = chargeService.addCharge(chargeDto);
		Map<String, String> resultMap = new HashMap<>();
		
		if(resultCharge > 0) {
			log.info("ChargeController chargeAdd() resultCharge 완료");
			 resultMap.put("result", "success");
			return resultMap;
			
		} else {
			log.error("ChargeContoller chargeAdd() resultCharge 실패");
			resultMap.put("result", "fail");
			return resultMap;
		}
		
	}
	
	/*
	 *	이용 가능한 쿠폰 체크
	 *	charge.html에서 ajax 요청 처리
	 *	전달받은 memberId와 couponCode를 TB coupon, TB coupon_use와 비교하여 validation check
	 *	사용 불가능한 쿠폰 : resultInt 0을 반환, 사용 가능한 쿠폰 : resultInt 1은 반환 ,couponValue를 반환
	 *	@param	session, couponUseDto
	 *	@return	checkedcouponUseDto
	 */
	@GetMapping(value = "/check-available-coupon")
	@ResponseBody
	public CouponUseDto checkAvailableCoupon(HttpSession session, CouponUseDto couponUseDto) {
		String memberId = (String) session.getAttribute("memberId");
		CouponUseDto checkedcouponUseDto = new CouponUseDto();				
		log.info("ChargeContoller checkAvailableCoupon() Start...");
		log.info("ChargeContoller checkAvailableCoupon() memberId -> {}", memberId);
		couponUseDto.setMemberId(memberId);
		log.info("ChargeContoller checkAvailableCoupon() couponUseDto.toString() -> {}", couponUseDto.toString());
		
		// 사용 결과만 가져옴 -> 사용 가능한 쿠폰, 사용 했던 쿠폰을 비교하여, 회원이 사용했던 이력이 있는 쿠폰의 결과를 가져와 사용가능 여부를 따짐
		int resultInt = chargeService.checkAvailableCoupon(couponUseDto);
		
		// 충전 금액과 쿠폰의 금액을 차감해 실제 충전에 사용되는 금액을 구하기 위해 쿠폰의 금액을 가져옴
		Long couponValue = chargeService.getAvailAmountCoupon(couponUseDto);
		
		checkedcouponUseDto.setResult(resultInt);
		checkedcouponUseDto.setCouponValue(couponValue);
		
		log.info("ChargeContoller checkAvailableCoupon resultInt-> {}", resultInt);
		log.info("ChargeContoller checkAvailableCoupon couponValue-> {}", couponValue);
		
		return checkedcouponUseDto;
	}
	
	/*
	 * 총보유밤부
	 * header-js에서 ajax 요청 처리
	 * html에서 전달받은 memberId의 총 보유 bamboo를 계산하여 반환
	 * @param	memberId
	 * @return	foundTotalBambooStr
	 */
	@GetMapping(path = "/members/total-bamboo")
	@ResponseBody
	public String  totalBambooByMemberId(@RequestParam("memberId") String memberId) {
		log.info("ChargeContoller totalBambooByMemberId Start...");
		log.info("ChargeContoller totalBambooByMemberId() memberId -> {}", memberId);
		
		Long foundTotalBambooByMemberId = chargeService.findTotalBambooByMemberId(memberId);
		
		String  foundTotalBambooByMemberIdStr =  Long.toString(foundTotalBambooByMemberId);
		
		log.info("ChargeContoller totalBambooByMemberId calculatedTotalBamboo -> {}", foundTotalBambooByMemberIdStr);
		
		return foundTotalBambooByMemberIdStr;
	}
	
	
}
	


