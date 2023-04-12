package com.kakao.jPanda.yjh.service;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Exchange;

public interface ExchangeService {
	List<Exchange> exchangedList(Exchange exchange);
	void exchangedUpdate(Long[] longExn);
}
