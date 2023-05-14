package com.kakao.jPanda.trade.service;

import java.util.List;

import com.kakao.jPanda.trade.domain.StatDto;
import com.kakao.jPanda.trade.domain.TalentDto;
import com.kakao.jPanda.trade.domain.TradeDto;

public interface TradeService {
	
	List<StatDto> findStatListByMemberId(String memberId);

	List<TradeDto> findTradeListByMemberId(String memberId, String tradeDto);

	int modifyTalentByTalentNo(String talentNo, TalentDto talentDto);

	int removeRefundByrefundPurchaseNo(String refundPurchaseNo);

	TalentDto findTalentByTalentNo(String talentNo);

	int addExchangeByTalentNo(TalentDto talentDto);

	int addRefund(String memberId ,TradeDto tradeDto);

	int modifyExchangeStatusByTalentNo(String talentNo);

	TradeDto findExchangeByExchangeNo(String exchangeNo);

	TradeDto findRefundByRefundPurchaseNo(String refundPurchaseNo);

	TradeDto findTradeByTalentNo(String talentNo);
	
}