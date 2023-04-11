package com.kakao.jPanda.yjh.service;

import java.util.List;

import org.springframework.ui.Model;

import com.kakao.jPanda.yjh.domain.Exchange;

public interface ExchangeService {
	List<Exchange> exchangedList(Model model);
}
