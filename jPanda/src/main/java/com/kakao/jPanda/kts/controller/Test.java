package com.kakao.jPanda.kts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
	
	@GetMapping("/footer")
	public String footerTest() {
		return "/";
	}
}
