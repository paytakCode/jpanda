package com.kakao.jPanda.jst.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.SellListDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TradeDaoImpl implements TradeDao{
	
	private final SqlSession sqlSession;
	
	@Autowired
	public TradeDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<SellListDto> getSellListById(String memberId) {
		log.info("TradeDao getSellListById memberId check : " + memberId);
		List<SellListDto> sellList = sqlSession.selectList("selectSellList", memberId);
		log.info("sellList.size : " + sellList.size());
		return sellList;
	}

	@Override
	public List<BuyListDto> getBuyListById(String memberId) {
		log.info("TradeDao getBuyListById memberId check : " + memberId);
		List<BuyListDto> buyList = sqlSession.selectList("selectBuyList", memberId);
		log.info("buyList.size : " + buyList.size());
		return buyList;
	}

}
