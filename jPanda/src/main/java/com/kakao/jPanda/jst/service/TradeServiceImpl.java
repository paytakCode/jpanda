package com.kakao.jPanda.jst.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.jst.dao.TradeDao;
import com.kakao.jPanda.jst.domain.StatDto;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TradeServiceImpl implements TradeService{
	
	private final TradeDao tradeDao;
	
	@Autowired
	public TradeServiceImpl(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
	
	@Override
	public List<StatDto> findStatListByMemberId(String memberId) {
		List<StatDto> statList = new ArrayList<StatDto>();
		StatDto buyStatDto = tradeDao.selectBuyStatByMemberId(memberId); 
		StatDto sellStatDto = tradeDao.selectSellStatByMemberId(memberId); 
		StatDto refundStatDto = tradeDao.selectRefundStatByMemberId(memberId);
		
		buyStatDto.setStatType("buy");
		sellStatDto.setStatType("sell");
		refundStatDto.setStatType("refund");
		
		statList.add(buyStatDto);
		statList.add(sellStatDto);
		statList.add(refundStatDto);
		
		return statList;
	}
	
	@Override
	public List<TradeDto> findTradeListByMemberId(String memberId, String listType) {
		List<TradeDto> tradeList = new ArrayList<TradeDto>();
	
		switch (listType) {
			case "sell":
				tradeList = tradeDao.selectTradeSellListByMemberId(memberId);
				//리스트 출력을 위한 listType set
				tradeList.forEach(e -> e.setListType("sell"));
				break;
			case "buy":
				tradeList = tradeDao.selectTradeBuyListByMemberId(memberId);
				//리스트 출력을 위한 listType set
				tradeList.forEach(e -> e.setListType("buy"));
				break;
				
			case "refund":
				tradeList = tradeDao.selectTradeRefundListByMemberId(memberId);
				//리스트 출력을 위한 listType set
				tradeList.forEach(e -> e.setListType("refund"));
				break;
				
			case "all":
				List<TradeDto> tempList = tradeDao.selectTradeSellListByMemberId(memberId);
				//리스트 출력을 위한 listType set
				tempList.forEach(e -> e.setListType("sell"));
				tradeList.addAll(tempList);
				
				tempList = tradeDao.selectTradeBuyListByMemberId(memberId);
				tempList.forEach(e -> e.setListType("buy"));
				tradeList.addAll(tempList);
				
				tempList = tradeDao.selectTradeRefundListByMemberId(memberId);
				tempList.forEach(e -> e.setListType("refund"));
				tradeList.addAll(tempList);
				
				//RegDate, SubmitDate, PurchaseDate 서로비교, 오름차순 정렬
				Collections.sort(tradeList, new Comparator<TradeDto>() {
				    @Override
				    public int compare(TradeDto o1, TradeDto o2) {
				        // RegDate 비교
				        if (o1.getRegDate() != null && o2.getRegDate() != null) {
				            return o1.getRegDate().compareTo(o2.getRegDate());
				        } else if (o1.getRegDate() != null) {
				            return 1;
				        } else if (o2.getRegDate() != null) {
				            return -1;
				        }
				        
				        // SubmitDate 비교
				        if (o1.getRefundSubmitDate() != null && o2.getRefundSubmitDate() != null) {
				            return o1.getRefundSubmitDate().compareTo(o2.getRefundSubmitDate());
				        } else if (o1.getRefundSubmitDate() != null) {
				            return 1;
				        } else if (o2.getRefundSubmitDate() != null) {
				            return -1;
				        }
				        
				        // PurchaseDate 비교
				        if (o1.getPurchaseDate() != null && o2.getPurchaseDate() != null) {
				            return o1.getPurchaseDate().compareTo(o2.getPurchaseDate());
				        } else if (o1.getPurchaseDate() != null) {
				            return 1;
				        } else if (o2.getPurchaseDate() != null) {
				            return -1;
				        }
				        
				        // 모든 필드 값이 null 인 경우
				        return 0;
				    }
				});


				break;

		}
		
		log.info("findTradeListByMemberId tradeList.size() : " + tradeList.size());
		
		return tradeList;
	}

	@Override
	public int modifyTalentStatusByTalentNo(String talentNo) {
		int result = 0;
		
		try {
			result = tradeDao.updateTalentStatusByTalentNo(talentNo);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int removeRefundByrefundPurchaseNo(String purchaseNo) {
		int result = 0;
		
		try {
			result = tradeDao.deleteRefundByrefundPurchaseNo(purchaseNo);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return result;
	}

	@Override
	public TalentDto findTalentByTalentNo(String talentNo) {
		TalentDto talentDto = null;
		
		try {
			talentDto = tradeDao.selectTalentByTalentNo(talentNo);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return talentDto;
	}

	@Override
	public int addExchangeByTalentNo(TalentDto talentDto) {
		int result = 0;
		
		try {
			result = tradeDao.insertExchangeByTalentNo(talentDto);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int addRefund(HttpSession session, TradeDto tradeDto) {
		int result = 0;
		tradeDto.setBuyerId((String)session.getAttribute("memberId"));
		log.info("addRefund tradeDto.getBuyerId() : " + tradeDto.getBuyerId());
		
		try {
			result = tradeDao.insertTalentRefund(tradeDto);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return result; 
		
	}

}//end class


//이전코드
//List<TradeDto> tradeList = tradeDao.selectTradeListByParaMap(paraMap);
//
//tradeList.stream()
//		 .filter(DeduplicationUtils.distinctByKey(e -> e.getTalentNo()))
//		 .forEach(e -> {
//			 if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() == null) {
//					 e.setListType("buy");
//				 e.setTalentStatus("구매완료");
//			 } else if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() != null) {
//			 	 e.setListType("refund");
//			 } else if (memberId.equals(e.getSellerId())) {
//				 e.setListType("sell");
//			 } 
//		 });
//@Override
//public List<SellListDto> getSellList(String memberId) {
//	return tradeDao.getSellListById(memberId);
//}
//
//@Override
//public List<BuyListDto> getBuyList(String memberId) {
//	List<BuyListDto> buyList = tradeDao.getBuyListById(memberId);
//	buyList.forEach(e -> e.setStatus("구매완료"));
//	return buyList;
//}
//
//@Override
//public List<RefundListDto> getRefundList(String memberId) {
//	return tradeDao.getRefundListById(memberId);
//}
//
//
//@Override
///**
// *  listType을 셋팅하여 Ajax 통신시 콜백함수 if문에서 조건으로 사용
// */
//public List<TradeListDto> getAllList(String memberId) {
//	List<TradeListDto> allList = tradeDao.getAllListById(memberId);
//	allList.forEach(e -> {
//		if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() == null) {
//			e.setListType("구매");
//			e.setStatus("구매완료");
//		} else if (memberId.equals(e.getBuyerId()) && e.getRefundStatus() != null) {
//			e.setListType("환불");
//		} else if (memberId.equals(e.getSellerId())) {
//			e.setListType("판매");
//		} 
//		
//	});
//	
//	return allList;
//}
