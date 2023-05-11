package com.kakao.jPanda.trade.dao;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.trade.domain.StatDto;
import com.kakao.jPanda.trade.domain.TalentDto;
import com.kakao.jPanda.trade.domain.TradeDto;

public interface TradeDao {

	List<TradeDto> selectTradeListByParaMap(Map<String, Object> paraMap);

	int updateTalentStatusByTalentNo(String talentNo);

	int deleteRefundByRefundPurchaseNo(String purchaseNo);

	TalentDto selectTradeTalentByTalentNo(String talentNo);

	int insertExchangeByTalentNo(TalentDto talentDto);
	
	StatDto selectSellStatByMemberId(String memberId);
	
	StatDto selectBuyStatByMemberId(String memberId);
	
	StatDto selectRefundStatByMemberId(String memberId);

	int insertTalentRefund(TradeDto tradeDto);

	List<TradeDto> selectTradeSellListByMemberId(String memberId);

	List<TradeDto> selectTradeBuyListByMemberId(String memberId);

	List<TradeDto> selectTradeRefundListByMemberId(String memberId);

	int updateExchangeStatusByTalentNo(String talentNo);

	int updateTradeTalent(TalentDto talentDto);

	TradeDto selectExchangeByExchangeNo(String exchangeNo);

	TradeDto selectRefundByRefundPurchaseNo(String refundPurchaseNo);

	TradeDto selectTradeByTalentNo(String talentNo);

}//end class