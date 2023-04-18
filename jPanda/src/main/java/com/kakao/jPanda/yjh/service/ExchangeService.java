package com.kakao.jPanda.yjh.service;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Exchange;

public interface ExchangeService {
	List<Exchange> findExchange();
//	void modifyExchangeByExchangeNos(String[] exchangeNo, String status);
	void modifyExchangeStatusByExchangeNos(String[] exchangeNoArray, String status);
	
}
