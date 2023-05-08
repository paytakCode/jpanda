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
	public int deleteRefundByRefundPurchaseNo(String refundPurchaseNo) {
		log.info("deleteRefundByRefundPurchaseNo refundPurchaseNo : " + refundPurchaseNo);
		int result = sqlSession.update("deleteRefundByRefundPurchaseNo", refundPurchaseNo);
		log.info("deleteRefundByRefundPurchaseNo result : " + result);
		return result;
	}

	@Override
	public TalentDto selectTradeTalentByTalentNo(String talentNo) {
		log.info("selectTradeTalentByTalentNo talentNo : " + talentNo);
		TalentDto talentDto = sqlSession.selectOne("selectTradeTalentByTalentNo", talentNo);
		log.info("selectTradeTalentByTalentNo talentDto : " + talentDto.toString());
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
	public int updateTradeTalent(TalentDto talentDto) {
		log.info("updateTradeTalent talentDto : " + talentDto);
		int result = sqlSession.update("updateTradeTalent", talentDto);
		log.info("updateTradeTalent result : " + result);
		return result;
	}

	@Override
	public TradeDto selectExchangeByExchangeNo(String exchangeNo) {
		log.info("selectExchangeByExchangeNo exchangeNo : " + exchangeNo);
		TradeDto tradeDto = sqlSession.selectOne("selectTradeExchangeByExchangeNo", exchangeNo);
		log.info("selectExchangeByExchangeNo tradeDto : " + tradeDto);
		return tradeDto;
	}

	@Override
	public TradeDto selectRefundByRefundPurchaseNo(String refundPurchaseNo) {
		log.info("selectRefundByRefundPurchaseNo refundPurchaseNo : " + refundPurchaseNo);
		TradeDto tradeDto = sqlSession.selectOne("selectTradeRefundByRefundPurchaseNo", refundPurchaseNo);
		log.info("selectRefundByRefundPurchaseNo tradeDto : " + tradeDto);
		return tradeDto;
	}

	@Override
	public TradeDto selectTradeByTalentNo(String talentNo) {
		log.info("selectTradeByTalentNo talentNo : " + talentNo);
		TradeDto tradeDto = sqlSession.selectOne("selectTradeByTalentNo", talentNo);
		log.info("selectTradeByTalentNo tradeDto : " + tradeDto);
		return tradeDto;
	}

}//end class