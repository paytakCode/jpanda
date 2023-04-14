package com.kakao.jPanda.jst.service;

import java.util.List;

import com.kakao.jPanda.jst.domain.TradeListDto;
import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;

public interface TradeService {
	
//	List<SellListDto> getSellList(String memberId);
//	
//	List<BuyListDto> getBuyList(String memberId);
//	
//	List<RefundListDto> getRefundList(String memberId);
//	
//	List<TradeListDto> getAllList(String memberId);
//	
	StatDto getStat(String memberId);

	List<TradeListDto> getTradeList(String memberId, TradeListDto tradeListDto);

	int endSell(String talentNo);

	int cancleRefund(String purchaseNo);

	TalentDto getTalentByTalentNo(String talentNo);

	int submitExchange(TalentDto talentDto);

}
