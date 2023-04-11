package com.kakao.jPanda.jst.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.jst.domain.BuyListDto;
import com.kakao.jPanda.jst.domain.RefundListDto;
import com.kakao.jPanda.jst.domain.SellListDto;
import com.kakao.jPanda.jst.domain.StatDto;

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

	@Override
	public List<RefundListDto> getRefundListById(String memberId) {
		log.info("TradeDao getRefundListById memberId check : " + memberId);
		List<RefundListDto> refundList = sqlSession.selectList("selectRefundList", memberId);
		log.info("refundList.size : " + refundList.size());
		return refundList;
	}

	@Override
	public StatDto getStatById(String memberId) {
		log.info("TradeDao getStatById Stat check : " + memberId);
		StatDto stat = sqlSession.selectOne("selectStat", memberId);
		log.info("stat.toString : " + stat.toString());
		return stat;
	}

}
