package com.kakao.jPanda.jst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.jst.dao.TradeDao;
import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;

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
		List<BuyListDto> buyList = tradeDao.getBuyListById(memberId);
		buyList.forEach(e -> e.setStatus("구매완료"));
		return buyList;
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
