package com.kakao.jPanda.yjh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.service.NoticeService;

@Controller
public class NoticeController {
	private final NoticeService noticeService;
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
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
		
		return "redirect:/notice";
	}
}
