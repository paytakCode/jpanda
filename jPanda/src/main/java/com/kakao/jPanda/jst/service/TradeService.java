package com.kakao.jPanda.jst.service;

import java.util.List;

import org.springframework.ui.Model;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;

public interface TradeService {
	
	List<SellListDto> getSellList(String memberId);
	
	List<BuyListDto> getBuyList(String memberId);
	
	List<RefundListDto> getRefundList(String memberId);

}
