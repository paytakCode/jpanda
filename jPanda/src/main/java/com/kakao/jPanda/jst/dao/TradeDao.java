package com.kakao.jPanda.jst.dao;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;

public interface TradeDao {

//	List<SellListDto> getSellListById(String memberId);
//
//	List<BuyListDto> getBuyListById(String memberId);
//
//	List<RefundListDto> getRefundListById(String memberId);
//
//	List<TradeListDto> getAllListById(String memberId);
//	
	

	List<TradeDto> selectTradeListByParaMap(Map<String, Object> paraMap);

	int updateTalentStatusByTalentNo(String talentNo);

	int deleteRefundByrefundPurchaseNo(String purchaseNo);

	TalentDto selectTalentByTalentNo(String talentNo);

	int insertExchangeByTalentNo(TalentDto talentDto);
	
	StatDto selectSellStatByMemberId(String memberId);
	
	StatDto selectBuyStatByMemberId(String memberId);
	
	StatDto selectRefundStatByMemberId(String memberId);

	int insertTalentRefund(TradeDto tradeDto);

	List<TradeDto> selectTradeSellListByMemberId(String memberId);

	List<TradeDto> selectTradeBuyListByMemberId(String memberId);

	List<TradeDto> selectTradeRefundListByMemberId(String memberId);

	int updateExchangeStatusByTalentNo(String talentNo);

}
