package com.kakao.jPanda.jst.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;

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
	public List<TradeDto> selectTradeListByParaMap(Map<String, Object> paraMap) {
		List<TradeDto> tradeList = sqlSession.selectList("selectTradeListByParaMap", paraMap);
		log.info("selectTradeListByParaMap tradeList.size() : " + tradeList.size());
		return tradeList;
	}

	@Override
	public int updateTalentStatusByTalentNo(String talentNo) {
		log.info("updateTalentStatusByTalentNo talentNo : " + talentNo);
		int result = sqlSession.update("updateTalentStatusByTalentNo", talentNo);
		log.info("updateTalentStatusByTalentNo result : " + result);
		return result;
	}

	@Override
	public int deleteRefundByrefundPurchaseNo(String refundPurchaseNo) {
		log.info("deleteRefundByrefundPurchaseNo refundPurchaseNo : " + refundPurchaseNo);
		int result = sqlSession.update("deleteRefundByrefundPurchaseNo", refundPurchaseNo);
		log.info("deleteRefundByPurchaseNo result : " + result);
		return result;
	}

	@Override
	public TalentDto selectTalentByTalentNo(String talentNo) {
		log.info("selectTalentByTalentNo talentNo : " + talentNo);
		TalentDto talentDto = sqlSession.selectOne("selectTalentByTalentNo", talentNo);
		log.info("selectTalentByTalentNo talentDto.getTalentNo() : " + talentDto.getTalentNo());
		return talentDto;
	}

	@Override
	public int insertExchangeByTalentNo(TalentDto talentDto) {
		log.info("insertExchangeByTalentNo talentDto : " + talentDto.toString());
		int result = sqlSession.insert("insertExchangeByTalentNo", talentDto);
		log.info("insertExchangeByTalentNo result : " + result);
		return result;
	}
	
	@Override
	public StatDto selectSellStatByMemberId(String memberId) {
		log.info("selectSellStat memberId check : " + memberId);
		StatDto statDto = sqlSession.selectOne("selectSellStatByMemberId", memberId);
		log.info("selectSellStat statDto : " + statDto.toString());
		return statDto;
	}

	@Override
	public StatDto selectBuyStatByMemberId(String memberId) {
		log.info("selectBuyStat memberId check : " + memberId);
		StatDto statDto = sqlSession.selectOne("selectBuyStatByMemberId", memberId);
		log.info("selectBuyStat statDto : " + statDto.toString());
		return statDto;
	}

	@Override
	public StatDto selectRefundStatByMemberId(String memberId) {
		log.info("selectRefundStat memberId check : " + memberId);
		StatDto statDto = sqlSession.selectOne("selectRefundStatByMemberId", memberId);
		log.info("selectRefundStat statDto : " + statDto.toString());
		return statDto;
	}

	@Override
	public int insertTalentRefund(TradeDto tradeDto) {
		int result = 0;
		log.info("insertTalentRefund tradeDto : " + tradeDto.toString());
		result = sqlSession.insert("insertTalentRefund", tradeDto);
		log.info("insertTalentRefund result : " + result);
		return result;
		
	}

	@Override
	public List<TradeDto> selectTradeSellListByMemberId(String memberId) {
		log.info("selectTradeSellListByMemberId memberId : " + memberId);
		List<TradeDto> tradeList = sqlSession.selectList("selectTradeSellListByMemberId", memberId);
		log.info("selectTradeSellListByMemberId tradeList.size() : " + tradeList.size());
		return tradeList;
	}

	@Override
	public List<TradeDto> selectTradeBuyListByMemberId(String memberId) {
		log.info("selectTradeBuyListByMemberId memberId : " + memberId);
		List<TradeDto> tradeList = sqlSession.selectList("selectTradeBuyListByMemberId", memberId);
		log.info("selectTradeBuyListByMemberId tradeList.size() : " + tradeList.size());
		return tradeList;
	}

	@Override
	public List<TradeDto> selectTradeRefundListByMemberId(String memberId) {
		log.info("selectTradeRefundListByMemberId memberId : " + memberId);
		List<TradeDto> tradeList = sqlSession.selectList("selectTradeRefundListByMemberId", memberId);
		log.info("selectTradeRefundListByMemberId tradeList.size() : " + tradeList.size());
		return tradeList;
	}

	@Override
	public int updateExchangeStatusByTalentNo(String talentNo) {
		log.info("updateExchangeStatusByTalentNo talentNo : " + talentNo);
		int result = sqlSession.update("updateExchangeStatusByTalentNo", talentNo);
		log.info("updateExchangeStatusByTalentNo result : " + result);
		return result;
	}

	@Override
	public int updateTalentStatus(TalentDto talentDto) {
		log.info("updateTalentStatus talentDto : " + talentDto);
		int result = sqlSession.update("updateTalentStatus", talentDto);
		log.info("updateTalentStatus result : " + result);
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

