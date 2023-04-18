package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Exchange;

public interface ExchangeDao {
	List<Exchange> selectExchange();
//	void updateExchangeByExchangeNos(List<Long> listExchangeNo);
//	void updateExchangeByExchangeNosToCompanion(List<Long> listExchangeNo);
	Exchange selectExchangeByExchangeNo(Long exchangeNo);
	void updateExchange(Exchange exchange);
	
}
