package com.kakao.jPanda.jst.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;

public interface TradeService {
	
	List<StatDto> findStatListByMemberId(String memberId);

	List<TradeDto> findTradeListByMemberId(String memberId, String tradeDto);

	String modifyTalentByTalentNo(String talentNo, TalentDto talentDto);

	String removeRefundByrefundPurchaseNo(String refundPurchaseNo);

	TalentDto findTalentByTalentNo(String talentNo);

	String addExchangeByTalentNo(TalentDto talentDto);

	String addRefund(HttpSession session ,TradeDto tradeDto);

	String modifyExchangeStatusByTalentNo(String talentNo);

	TradeDto findExchangeByExchangeNo(String exchangeNo);

	TradeDto findRefundByRefundPurchaseNo(String refundPurchaseNo);
	
}