package com.kakao.jPanda.yjh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.domain.Talent;
import com.kakao.jPanda.yjh.service.CouponService;
import com.kakao.jPanda.yjh.service.ExchangeService;
import com.kakao.jPanda.yjh.service.NoticeService;
import com.kakao.jPanda.yjh.service.TalentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
	private final NoticeService noticeService;
	private final ExchangeService exchangeService;
	private final CouponService couponService;
	private final TalentService talentService;
	
	@GetMapping(value = "")
	public String home() {
		return "yjh/home";
	}
	
	@GetMapping(value = "/notice-form")
	public String noticeForm() {
		return "yjh/notice";
	}
	
	@PostMapping(value = "/notice")
	public String noticeAdd(Notice notice) {
		System.out.println("=====notice controller noticeAdd() start=====");
		noticeService.addNotice(notice);
		System.out.println("=====notice controller noticeAdd() end=====");
		
		return "redirect:/admin";
	}
	
	@GetMapping(value = "/exchange")
	public String exchangeList(Model model) {
		System.out.println("===== ExchangeController exchangeListByStatus() start =====");
		List<Exchange> exList = exchangeService.findExchange();
		
		model.addAttribute("exList", exList);
				
		return "yjh/exchange";
	}
	
	@PutMapping(value = "/exchange")
	public String exchangeModifyByExchangeNos(@RequestParam(name = "exchangeNo") String[] exchangeNoArray, @RequestParam String status) {
		System.out.println("===== ExchangeController exchangeUpdateToComplete() start =====");
		
		for(int i = 0; i < exchangeNoArray.length; i++) {
			System.out.println("exchangeNo"+exchangeNoArray[i].toString());
		}
//		exchangeService.modifyExchangeByExchangeNos(exchangeNo, status);
		exchangeService.modifyExchangeStatusByExchangeNos(exchangeNoArray, status);
		
		return "redirect:/admin";
	}
	
	@GetMapping(value = "/coupons-form")
	public String couponsForm() {
		System.out.println("===== Controller couponsForm() start =====");
		return "yjh/createCoupon";
	}
	
	@ResponseBody
	@PostMapping(value = "/coupons/coupons-no")
	public String genetateCouponNo() {
		System.out.println("===== Controller genetateCouponNo() start =====");
		return couponService.generateCouponNo();
	}
	
		
	@PostMapping(value = "/coupons")
	public String couponAdd(@RequestParam(name = "couponValue") String couponValue, @RequestParam(name = "couponNo") String couponNo) {
		System.out.println("===== Controller couponAdd() start =====");	
		couponService.addCoupon(couponValue, couponNo);
		
		return "redirect:/admin";
	}
	
	@GetMapping(value = "/talent")
	public String talentList(Model model) {
		System.out.println("===== Controller talentList() start =====");
		List<Talent> talentList =  talentService.findTalent();
		
		model.addAttribute("talentList", talentList);
		
		return "yjh/talent";
	}
	
}	
