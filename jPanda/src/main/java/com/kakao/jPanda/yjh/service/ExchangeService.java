package com.kakao.jPanda.yjh.service;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Exchange;

public interface ExchangeService {
	List<Exchange> findExchangeByStatus(Exchange exchange);
	void exchangedUpdate(List<Long> listExn);
	void exchangedUpdateToCompanion(List<Long> longListExhangeNo);
	
	
}
