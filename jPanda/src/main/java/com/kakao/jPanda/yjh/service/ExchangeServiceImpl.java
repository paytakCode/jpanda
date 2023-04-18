package com.kakao.jPanda.yjh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.ExchangeDao;
import com.kakao.jPanda.yjh.domain.Exchange;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
	private final ExchangeDao exchangeDao;

	@Override
	public List<Exchange> findExchange() {
		System.out.println("===== ExchageService findExchangeByStatus() start =====");
		List<Exchange> exList = exchangeDao.selectExchange();
		return exList;
	}

//	@Override
//	public void modifyExchangeByExchangeNos(String[] exchangeNo, String status) {
//		System.out.println("===== ExchangeService modifyExchangeByExchangeNos() start =====");
//
//		Long[] longExchangeNo = new Long[exchangeNo.length];
//		
//		for(int i = 0; i < exchangeNo.length; i++) {
//			longExchangeNo[i] = Long.parseLong(exchangeNo[i]);
//			System.out.println(longExchangeNo[i]);
//		} 
//		
//		List<Long> listExchangeNo = new ArrayList<Long>();
//		
//		for(Long a : longExchangeNo) {
//			listExchangeNo.add(a);
//			System.out.println(listExchangeNo.toString());
//		}
//		if(status.equals("환전완료")) {
//			System.out.println("환전완료 로직");
//			exchangeDao.updateExchangeByExchangeNos(listExchangeNo);
//			
//		} else if(status.equals("반려")) {
//			System.out.println("반려로직");
//			exchangeDao.updateExchangeByExchangeNosToCompanion(listExchangeNo);
//		}
//		
//	}
	
	@Override
	public void modifyExchangeStatusByExchangeNos(String[] exchangeNoArray, String status) {
		Long exchangeNo = null;
		Exchange exchange = null;
		
		for(String exchangeNoStr : exchangeNoArray) {
			exchangeNo = Long.parseLong(exchangeNoStr);
			exchange = exchangeDao.selectExchangeByExchangeNo(exchangeNo);
			exchange.setStatus(status);
			System.out.println("exchange : "+exchange.toString());
			exchangeDao.updateExchange(exchange);
		}
	}
}
