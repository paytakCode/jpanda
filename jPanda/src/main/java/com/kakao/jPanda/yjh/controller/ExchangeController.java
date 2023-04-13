package com.kakao.jPanda.yjh.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.service.ExchangeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExchangeController {
	private final ExchangeService exchangeService;
	
	@GetMapping(value = "/exchange")
	public String getExchangeList(Exchange exchange, Model model) {
		System.out.println("===== ExchangeController exchange start =====");
		List<Exchange> exList = exchangeService.exchangedList(exchange);
		
		model.addAttribute("exList", exList);
				
		return "yjh/exchange";
	}
	
	@PutMapping(value = "/exchange")
	public String exchangeStatusUpdate(@RequestParam(name = "exchangeNo") String[] exchangeNo) {
		System.out.println("===== ExchangeController exchangeUpdate start =====");	
		Long[] longExn = new Long[exchangeNo.length];
		
		for(int i = 0; i < exchangeNo.length; i++) {
			longExn[i] = Long.parseLong(exchangeNo[i]);
		}
		
		if(longExn != null) {
			exchangeService.exchangedUpdate(longExn);		
		}
			return "redirect:/";
		
//		exchangeService.changeStatus(exchangeNo);
	}
	
}
