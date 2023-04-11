package com.kakao.jPanda.yjh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.service.ExchangeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExchangeController {
	private final ExchangeService exchangeService;
	
	@GetMapping(value = "/exchange")
	public String exchange(Exchange exchange, Model model) {
		System.out.println("===== ExchangeController exchange start =====");
		List<Exchange> exList = exchangeService.exchangedList(exchange);
		
		model.addAttribute("exList", exList);
				
		return "yjh/exchange";
	}
	
	@PutMapping(value = "/exchange")
	public String exchangeUpdate(HttpServletRequest request, Exchange exchange) {
		System.out.println("===== ExchangeController exchangeUpdate start =====");
		exchangeService.exchangedUpdate(exchange);
		return "redirect:/";
	}
	
}
