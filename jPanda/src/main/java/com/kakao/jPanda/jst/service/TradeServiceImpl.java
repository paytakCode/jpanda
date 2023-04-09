package com.kakao.jPanda.jst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.kakao.jPanda.jst.dao.TradeDao;
import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.SellListDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TradeServiceImpl implements TradeService{
	
	private final TradeDao tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	@Override
	public List<SellListDto> getSellList(Model model) {
		List<SellListDto> sellList;
		log.info("serviceImpl id check : " + model.getAttribute("memberId"));
		String memberId = (String)model.getAttribute("memberId");
		sellList = tradeDao.getSellListById(memberId);
		return sellList;
	}

	@Override
	public List<BuyListDto> getBuyList(Model model) {
		String memberId = (String)model.getAttribute("memberId");
		List<BuyListDto> buyList = tradeDao.getBuyListById(memberId);
		return buyList;
	}

}
