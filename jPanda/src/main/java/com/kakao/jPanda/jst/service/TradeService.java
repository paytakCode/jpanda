package com.kakao.jPanda.jst.service;

import java.util.List;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;

public interface TradeService {
	
	List<SellListDto> getSellList(String memberId);
	
	List<BuyListDto> getBuyList(String memberId);
	
	List<RefundListDto> getRefundList(String memberId);
	
	StatDto getStat(String memberId);

}
