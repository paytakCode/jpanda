package com.kakao.jPanda.jst.dao;

import java.util.List;
import java.util.Map;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
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
	StatDto getStatById(String memberId);

	List<TradeListDto> getTradeList(Map<String, Object> paraMap);

	int updateTalentStatus(String talentNo);

	int updateRefundStatus(String purchaseNo);

	TalentDto selectTalent(String talentNo);

	int insertExchange(TalentDto talentDto);



}
