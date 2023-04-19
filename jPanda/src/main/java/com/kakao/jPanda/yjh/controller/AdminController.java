package com.kakao.jPanda.yjh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@GetMapping(value = "/notice") // ~~ /notices/{noticeNo}  @Pathvariable  ,   /notices   @Pathparam LocalDate date ?date="dsaffdsa" 
	public String noticeList(Model model) {
		System.out.println("===== NoticeController noticeList start =====");
		
		List<Notice> noticeList = noticeService.findNotice();
		model.addAttribute("noticeList", noticeList);
		
		return "yjh/notice";
	}
	
	@GetMapping(value = "/notices/{noticeNo}")
	public String noticeDetails(@PathVariable String noticeNo, Model model) {
		System.out.println("===== NoticeController noticeDetails start =====");
		System.out.println("noticeNo : "+noticeNo.toString());
		
		Notice notice = noticeService.findNoticeByNoticeNo(noticeNo);
				
		model.addAttribute("notice", notice);
		return "yjh/notice-content";
	}
	/*
	@GetMapping(value = "/notices") // ~~ /notices/{noticeNo}  @Pathvariable  ,   /notices   @Pathparam LocalDate date ?date="dsaffdsa" 
	public String noticeListByDate(@PathParam(value = "date") LocalDate date, Model model) {
		///service
		if(date == null) {
			//dao.selectlist();
		} else {
			//dao.selectOne(date);
		}
		
		model.addAttribute("noticeList", noticeList);
		
		return "yjh/notice";
	}
	*/
	
	@GetMapping(value = "/notice-form")
	public String noticeForm() {
		return "yjh/notice-form";
	}
	
	@ResponseBody
	@PostMapping(value = "/notice")
	public String noticeAdd(Notice notice) {
		System.out.println("=====notice controller noticeAdd() start=====");
		String resultStr = noticeService.addNotice(notice);
		
		return resultStr;
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
			System.out.println("exchangeNo : "+exchangeNoArray[i].toString());
		}
		
		exchangeService.modifyExchangeStatusByExchangeNos(exchangeNoArray, status);
		
		return "redirect:/admin";
	}
	

	/*
	@PutMapping(value = "/exchanges/{exchangeNos}") // /exchanges/{exchangeNos}?status="반려" /exchnages/{exchange.exchangeNo}
	public String exchangeModifyByExchangeNosWithStatus(@PathVariable(name = "exchangeNos") String[] exchangeNoArray, @RequestParam String status) {
		System.out.println("===== ExchangeController exchangeUpdateToComplete() start =====");
		
		for(int i = 0; i < exchangeNoArray.length; i++) {
			System.out.println("exchangeNo : "+exchangeNoArray[i].toString());
		}
		
		exchangeService.modifyExchangeStatusByExchangeNos(exchangeNoArray, status);
		
		return "redirect:/admin";
	}
	*/
	
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
	
		
	@PostMapping(value = "/coupon") 
	public String couponAdd(@RequestParam(name = "couponValue") String couponValue, @RequestParam(name = "couponNo") String couponNo) {
		System.out.println("===== Controller couponAdd() start =====");	
		couponService.addCoupon(couponValue, couponNo);
		
		return "redirect:/admin";
	}
	
	@GetMapping(value = "/notice/{noticeNo}/modify-form")
	public String noticeModifyForm(@PathVariable String noticeNo, Model model) {
		System.out.println("modify noticeNo : "+noticeNo);
		Notice notice = noticeService.findNoticeByNoticeNo(noticeNo);
		
		model.addAttribute("notice", notice);
		
		return "yjh/notice-modifyFrom";
	}
	
	@ResponseBody
	@PutMapping(value = "/notice/{noticeNo}/modify")
	public String noticeModifyByNoticeNo(Notice notice) {
		System.out.println("===== NoticeController noticeModifyByNoticeNo() start =====");
		System.out.println("notice : "+notice.toString());
		
		String resultStr = noticeService.modifyNotice(notice);
		
		return resultStr;
	}
	
	@GetMapping(value = "/talents") // talents
	public String talentList(Model model) {
		System.out.println("===== Controller talentList() start =====");
		List<Talent> talentList =  talentService.findTalent();
		
		model.addAttribute("talentList", talentList);
		
		return "yjh/talent";
	}
	
	@PutMapping(value = "/talents")
	public String talentModifyByTalentNos() {
		System.out.println("===== TalentController talentModifyByTalentNos() start =====");
		
		
		
		return "";
	}
	
}	
