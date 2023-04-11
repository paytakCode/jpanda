package com.kakao.jPanda.jst.dao;

import java.util.List;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;

public interface TradeDao {

	List<SellListDto> getSellListById(String memberId);

	List<BuyListDto> getBuyListById(String memberId);

	List<RefundListDto> getRefundListById(String memberId);

	StatDto getStatById(String memberId);

}
