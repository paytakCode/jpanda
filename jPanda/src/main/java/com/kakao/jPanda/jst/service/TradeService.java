package com.kakao.jPanda.jst.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpSession;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;
import com.kakao.jPanda.jst.domain.TradeSearchDto;

public interface TradeService {
	
	List<StatDto> findStatListByMemberId(String memberId);

	List<TradeDto> findTradeListByMemberId(String memberId, String tradeDto);

	String modifyTalentByTalentNo(String talentNo, TalentDto talentDto);

	String removeRefundByrefundPurchaseNo(String refundPurchaseNo);

	TalentDto findTalentByTalentNo(String talentNo);

	String addExchangeByTalentNo(TalentDto talentDto);

	String addRefund(HttpSession session ,TradeDto tradeDto);

	String modifyExchangeStatusByTalentNo(String talentNo);

	CompletableFuture<List<TradeDto>> tradeChangeListener(TradeSearchDto tradeSearchDto);

}

//이전 코드

//List<SellListDto> getSellList(String memberId);
//
//List<BuyListDto> getBuyList(String memberId);
//
//List<RefundListDto> getRefundList(String memberId);
//
//List<TradeListDto> getAllList(String memberId);
//
