package com.kakao.jPanda.yjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {
	@GetMapping(value = "/")
	public String home() {
		return "yjh/home";
	}
}
