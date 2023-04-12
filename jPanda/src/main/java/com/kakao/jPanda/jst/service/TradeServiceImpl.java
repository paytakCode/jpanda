package com.kakao.jPanda.jst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.jst.dao.TradeDao;
import com.kakao.jPanda.jst.domain.TradeListDto;

import lombok.extern.slf4j.Slf4j;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;

@Service
@Transactional
@Slf4j
public class TradeServiceImpl implements TradeService{
	
	private final TradeDao tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
	
//	@Override
//	public List<SellListDto> getSellList(String memberId) {
//		return tradeDao.getSellListById(memberId);
//	}
//
//	@Override
//	public List<BuyListDto> getBuyList(String memberId) {
//		List<BuyListDto> buyList = tradeDao.getBuyListById(memberId);
//		buyList.forEach(e -> e.setStatus("구매완료"));
//		return buyList;
//	}
//
//	@Override
//	public List<RefundListDto> getRefundList(String memberId) {
//		return tradeDao.getRefundListById(memberId);
//	}
//
	@Override
	public StatDto getStat(String memberId) {
		return tradeDao.getStatById(memberId);
	}
//
//	@Override
//	/**
//	 *  listType을 셋팅하여 Ajax 통신시 콜백함수 if문에서 조건으로 사용
//	 */
//	public List<TradeListDto> getAllList(String memberId) {
//		List<TradeListDto> allList = tradeDao.getAllListById(memberId);
//		allList.forEach(e -> {
//			if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() == null) {
//				e.setListType("구매");
//				e.setStatus("구매완료");
//			} else if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() != null) {
//				e.setListType("환불");
//			} else if (memberId.equals(e.getSellerId())) {
//				e.setListType("판매");
//			} 
//			
//		});
//		
//		return allList;
//	}
	
	@Override
	public List<TradeListDto> getTradeList(String memberId, TradeListDto tradeListDto) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("memberId", memberId);
		paraMap.put("tradeListDto", tradeListDto);
		
		log.info("TradeServiceImpl getTradeList paraMap.size() : " + paraMap.size()); 
		log.info("TradeServiceImpl getTradeList paraMap.get(memberId) : " + paraMap.get("memberId")); 
		log.info("TradeServiceImpl getTradeList paraMap.get(tradeListDto) : " + paraMap.get("tradeListDto")); 
		
		List<TradeListDto> tradeList = tradeDao.getTradeList(paraMap);
		
		tradeList.forEach(e -> {
			if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() == null) {
				e.setListType("buy");
				e.setStatus("구매완료");
			} else if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() != null) {
				e.setListType("refund");
			} else if (memberId.equals(e.getSellerId())) {
				e.setListType("sell");
			} 
				System.out.println(e.getSellCount());
		});			
		
		return tradeList;
	}

}
