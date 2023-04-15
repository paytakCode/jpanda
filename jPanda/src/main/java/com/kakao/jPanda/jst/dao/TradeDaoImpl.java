package com.kakao.jPanda.jst.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
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

	@Override
	public int updateRefundStatus(String purchaseNo) {
		int result = sqlSession.update("updateRefundStatus", purchaseNo);
		return result;
	}

	@Override
	public TalentDto selectTalent(String talentNo) {
		TalentDto talentDto = sqlSession.selectOne("selectTalent", talentNo);
		log.info("talentDto getTitle() : " + talentDto.getTitle());
		return talentDto;
	}

	@Override
	public int insertExchange(TalentDto talentDto) {
		int result = sqlSession.insert("insertExchange", talentDto);
		log.info("result : " + result);
		return result;
	}
	
	@Override
	public StatDto selectSellStat(String memberId) {
		log.info("selectSellStat memberId check : " + memberId);
		StatDto statDto = sqlSession.selectOne("selectSellCount", memberId);
		log.info("selectSellStat statDto : " + statDto.toString());
		return statDto;
	}

	@Override
	public StatDto selectBuyStat(String memberId) {
		log.info("selectBuyStat memberId check : " + memberId);
		StatDto statDto = sqlSession.selectOne("selectBuyCount", memberId);
		log.info("selectBuyStat statDto : " + statDto.toString());
		return statDto;
	}

	@Override
	public StatDto selectRefundStat(String memberId) {
		log.info("selectRefundStat memberId check : " + memberId);
		StatDto statDto = sqlSession.selectOne("selectRefundCount", memberId);
		log.info("selectRefundStat statDto : " + statDto.toString());
		return statDto;
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

