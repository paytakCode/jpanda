package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Exchange;

public interface ExchangeDao {
	List<Exchange> selectExchangeByStatus(Exchange exchange);
	void exchangedUpdate(List<Long> listExn);
	void exchangedUpdateToCompanion(List<Long> longListExhangeNo);
	
	
}
