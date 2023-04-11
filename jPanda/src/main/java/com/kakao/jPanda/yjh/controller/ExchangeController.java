package com.kakao.jPanda.yjh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.jPanda.yjh.domain.Exchange;

@Controller
public class ExchangeController {

	@GetMapping(value = "/exchange")
	public String exchange(Model model) {
		List<Exchange> exList = 
		return "yjh/exchange";
	}
}
