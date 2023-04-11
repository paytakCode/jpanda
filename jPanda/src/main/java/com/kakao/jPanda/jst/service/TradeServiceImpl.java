package com.kakao.jPanda.jst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.kakao.jPanda.jst.dao.TradeDao;
import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class TradeServiceImpl implements TradeService{
	
	private final TradeDao tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	@Override
	public List<SellListDto> getSellList(String memberId) {
		return tradeDao.getSellListById(memberId);
	}

	@Override
	public List<BuyListDto> getBuyList(String memberId) {
		return tradeDao.getBuyListById(memberId);
	}

	@Override
	public List<RefundListDto> getRefundList(String memberId) {
		return tradeDao.getRefundListById(memberId);
	}

	@Override
	public StatDto getStat(String memberId) {
		return tradeDao.getStatById(memberId);
	}

}
