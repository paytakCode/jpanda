package com.kakao.jPanda.jst.dao;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeListDto;

public interface TradeDao {

//	List<SellListDto> getSellListById(String memberId);
//
//	List<BuyListDto> getBuyListById(String memberId);
//
//	List<RefundListDto> getRefundListById(String memberId);
//
//	List<TradeListDto> getAllListById(String memberId);
//	
	

	List<TradeListDto> getTradeList(Map<String, Object> paraMap);

	int updateTalentStatus(String talentNo);

	int updateRefundStatus(String purchaseNo);

	TalentDto selectTalent(String talentNo);

	int insertExchange(TalentDto talentDto);
	
	StatDto selectSellStat(String memberId);
	
	StatDto selectBuyStat(String memberId);
	
	StatDto selectRefundStat(String memberId);

}
