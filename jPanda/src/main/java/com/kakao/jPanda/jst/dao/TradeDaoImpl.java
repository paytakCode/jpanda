package com.kakao.jPanda.jst.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TradeListDto;

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
	public StatDto getStatById(String memberId) {
		log.info("TradeDao getStatById memberId check : " + memberId);
		StatDto stat = sqlSession.selectOne("selectStat", memberId);
		log.info("stat.toString : " + stat.toString());
		return stat;
	}

	@Override
	public List<TradeListDto> getTradeList(Map<String, Object> paraMap) {
		List<TradeListDto> tradeList = sqlSession.selectList("selectTradeList", paraMap);
		log.info("TradeDao getTradeList tradeList.size() : " + tradeList.size());
		return tradeList;
	}

	@Override
	public int updateTalentStatus(String talentNo) {
		
		int result = sqlSession.update("updateTalentStatus", talentNo);
		log.info("result : " + result);
		return result;
	}

}//end class

//이전코드

//@Override
//public List<SellListDto> getSellListById(String memberId) {
//	log.info("TradeDao getSellListById memberId check : " + memberId);
//	List<SellListDto> sellList = sqlSession.selectList("selectSellList", memberId);
//	log.info("sellList.size : " + sellList.size());
//	return sellList;
//}
//
//@Override
//public List<BuyListDto> getBuyListById(String memberId) {
//	log.info("TradeDao getBuyListById memberId check : " + memberId);
//	List<BuyListDto> buyList = sqlSession.selectList("selectBuyList", memberId);
//	log.info("buyList.size : " + buyList.size());
//	return buyList;
//}
//
//@Override
//public List<RefundListDto> getRefundListById(String memberId) {
//	log.info("TradeDao getRefundListById memberId check : " + memberId);
//	List<RefundListDto> refundList = sqlSession.selectList("selectRefundList", memberId);
//	log.info("refundList.size : " + refundList.size());
//	return refundList;
//}
//
//@Override
//public List<TradeListDto> getAllListById(String memberId) {
//	log.info("TradeDao getAllListById memberId check : " + memberId);
//	List<TradeListDto> allList = sqlSession.selectList("selectAllList", memberId);
//	log.info("TradeDao getAllListById allList.size() : " + allList.size());
//	return allList;
//}

