package com.kakao.jPanda.yjh.service;

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
	public List<Exchange> findExchangeByStatus(Exchange exchange) {
		System.out.println("===== Exchage Service exchangedList() start =====");
		List<Exchange> exList = exchangeDao.selectExchangeByStatus(exchange);
		return exList;
	}

	@Override
	public void exchangedUpdate(List<Long> listExn) {
		System.out.println("===== Exchange Service exchangedUpdate() start =====");
		exchangeDao.exchangedUpdate(listExn);
	}

	@Override
	public void exchangedUpdateToCompanion(List<Long> longListExhangeNo) {
		System.out.println("===== Exchange Service exchangeUpdateToCompanion() start =====");
		exchangeDao.exchangedUpdateToCompanion(longListExhangeNo);
		
	}

}
