package com.kakao.jPanda.yjh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.jPanda.yjh.domain.Coupon;
import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.service.CouponService;
import com.kakao.jPanda.yjh.service.ExchangeService;
import com.kakao.jPanda.yjh.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final NoticeService noticeService;
	private final ExchangeService exchangeService;
	private final CouponService couponService;
	
	@GetMapping(value = "/")
	public String home() {
		return "yjh/home";
	}
	
	@GetMapping(value = "/notice")
	public String notice() {
		return "yjh/notice";
	}
	
	@PostMapping(value = "/notice")
	public String insertNotice(Notice notice) {
		System.out.println("=====notice controller insertNotice() start=====");
		noticeService.insertNotice(notice);
		System.out.println("=====notice controller insertNotice() end=====");
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/exchange")
	public String getExchangeList(Exchange exchange, Model model) {
		System.out.println("===== ExchangeController getExchangeList start =====");
		List<Exchange> exList = exchangeService.exchangedList(exchange);
		
		model.addAttribute("exList", exList);
				
		return "yjh/exchange";
	}
	
	@PutMapping(value = "/exchange")
	public String exchangeStatusUpdateToComplete(@RequestParam(name = "exchangeNo") String[] exchangeNo) {
		System.out.println("===== ExchangeController exchangeUpdateToComplete() start =====");	

		Long[] longExn = new Long[exchangeNo.length];
		
		for(int i = 0; i < exchangeNo.length; i++) {
			longExn[i] = Long.parseLong(exchangeNo[i]);
			System.out.println(longExn[i]);
		} 
		
		List<Long> listExn = new ArrayList<Long>();
		
		for(Long lastExn : longExn) {
			listExn.add(lastExn);
			System.out.println(listExn);
		}
		
		if(longExn != null) {
			exchangeService.exchangedUpdate(listExn);
		}
			return "redirect:/";
	}
	
	@PutMapping(value = "/exchange/companion")
	public String exchangeStatusUpdateToCompanion(@RequestParam(name = "exchangeNo") String[] exchangeNo) {
		System.out.println("===== ExchangeController exchangeStatusUpdateToCompanion start =====");
		
		Long[] longExchangeNo = new Long[exchangeNo.length];
		
		for(int i = 0; i < exchangeNo.length; i++) {
			longExchangeNo[i] =  Long.parseLong(exchangeNo[i]);
		}
		
		List<Long> longListExhangeNo = new ArrayList<Long>();
		
		for(Long parameteList : longExchangeNo) {
			longListExhangeNo.add(parameteList);
		}
		
		exchangeService.exchangedUpdateToCompanion(longListExhangeNo);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/coupons")
	public String coupons() {
		System.out.println("Controller coupons() start");
		
		return "yjh/createCoupon";
	}
	
	@PostMapping(value = "/coupons/coupons-id")
	public String createCouponId(Model model) {
		UUID uuid = UUID.randomUUID();
		String couponNo = uuid.toString().substring(0, 6);
		System.out.println(couponNo);
		
		model.addAttribute("couponNo", couponNo);
		return "yjh/createCoupon";
	}
	
	@PostMapping(value = "/coupons")
	public String insertCouponData(@RequestParam(name = "couponValue") String couponValue, @RequestParam(name = "couponNo") String couponNo) {
		System.out.println("===== Controller insertCouponData() start =====");	
		Long longCouponValue = Long.parseLong(couponValue);
		
		Coupon coupon = new Coupon();
		coupon.setCouponNo(couponNo);
		coupon.setCouponValue(longCouponValue);

		System.out.println(coupon.toString());
		
		couponService.insertCouponData(coupon);
		
		return "redirect:/";
	}
}
