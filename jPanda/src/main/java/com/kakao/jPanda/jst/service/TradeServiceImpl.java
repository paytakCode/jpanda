package com.kakao.jPanda.jst.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
	
	//sell, buy, refundList 정렬을 위한 DATE 통합
	private Timestamp getDateForSort(TradeDto tradeDto) {
	    if (tradeDto.getStatusDate() != null) {
	        return tradeDto.getStatusDate();
	    } else if (tradeDto.getRefundSubmitDate() != null) {
	        return tradeDto.getRefundSubmitDate();
	    } else if (tradeDto.getPurchaseDate() != null){
	        return tradeDto.getPurchaseDate();
	    } else {
	    	return tradeDto.getRegDate();
	    }
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
				
				//RegDate, SubmitDate, PurchaseDate 서로비교, 내림차순 정렬
				Collections.sort(tradeList, (t1, t2) -> {
					return getDateForSort(t1).compareTo(getDateForSort(t2)) * -1;
				});
				
				break;

		}
		
		log.info("findTradeListByMemberId tradeList.size() : " + tradeList.size());
		
		return tradeList;
	}

	@Override
	public String modifyTalentByTalentNo(String talentNo, TalentDto talentDto) {
		int result = 0;
		//DB에 있는 Talent 조회
		TalentDto foundTalentDto = tradeDao.selectTalentByTalentNo(talentNo);
		
		//reflection을 통한 TalentDto field 값 목록
		Field[] fields = TalentDto.class.getDeclaredFields();
        for (Field field : fields) {
        	//필드 접근 허용
            field.setAccessible(true);
            
            try {
				if (field.get(talentDto) != null) {
					log.info("before : {}, after : {}",field.get(foundTalentDto)  , field.get(talentDto));
					//field.set(해당 필드값을 set 할 인스턴스, set 해줄 값)
					field.set(foundTalentDto, field.get(talentDto));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            
        }
		
		result = tradeDao.updateTalent(foundTalentDto);
		
		if (result > 0) {
			return "재등록에 성공하였습니다.";
		} else {
			return "재등록 요청에 실패하였습니다.";
		}
		
	}

	@Override
	public String removeRefundByrefundPurchaseNo(String purchaseNo) {
		int result = 0;
		
		result = tradeDao.deleteRefundByrefundPurchaseNo(purchaseNo);
		
		if (result > 0) {
			return "환불 취소 요청이 완료되었습니다.";
		} else {
			return "환불 취소 요청에 실패하였습니다.";
		}
	}

	@Override
	public TalentDto findTalentByTalentNo(String talentNo) {
		TalentDto talentDto = null;
		
		talentDto = tradeDao.selectTalentByTalentNo(talentNo);
		
		return talentDto;
	}

	@Override
	public String addExchangeByTalentNo(TalentDto talentDto) {
		int result = 0;
		
		if ( talentDto != null) {
			result = tradeDao.insertExchangeByTalentNo(talentDto);
		
			if (result > 0) {
				return "환전 신청이 완료되었습니다.";
			} else {
				return "환전 신청에 실패하였습니다.";
			}
			
		} else {
			return "fail submitExchange talentDto : null";
		}
		
	}

	@Override
	public String addRefund(HttpSession session, TradeDto tradeDto) {
		int result = 0;
		tradeDto.setBuyerId((String)session.getAttribute("memberId"));
		log.info("addRefund tradeDto.getBuyerId() : " + tradeDto.getBuyerId());
		
		result = tradeDao.insertTalentRefund(tradeDto);
		
		if (result > 0) {
			return "환불 신청이 완료되었습니다.";
		} else {
			return "환불 신청에 실패하였습니다.";
		}
		
		
	}

	@Override
	public String modifyExchangeStatusByTalentNo(String talentNo) {
		int result = 0;
		result = tradeDao.updateExchangeStatusByTalentNo(talentNo);
		
		if (result > 0) {
			return "환전등록 재신청에 성공하였습니다.";
		} else {
			return "환전등록 재신청에 실패하였습니다.";
		}
		
	}

}//end class


//이전코드

//Collections.sort(tradeList, (t1,t2) -> {
//if (t1.getRegDate() != null) {
//    if(t2.getRegDate() != null) {
//        return t1.getRegDate().compareTo(t2.getRegDate()) * -1;
//    } else if(t2.getRefundSubmitDate() != null) {
//        return t1.getRegDate().compareTo(t2.getRefundSubmitDate()) * -1;
//    } else if (t2.getPurchaseDate() != null) {
//        return t1.getRegDate().compareTo(t2.getPurchaseDate()) * -1;
//    }
//} else if (t1.getRefundSubmitDate() != null) {
//    if(t2.getRegDate() != null) {
//        return t1.getRefundSubmitDate().compareTo(t2.getRegDate()) * -1;
//    } else if(t2.getRefundSubmitDate() != null) {
//        return t1.getRefundSubmitDate().compareTo(t2.getRefundSubmitDate()) * -1;
//    } else if (t2.getPurchaseDate() != null) {
//        return t1.getRefundSubmitDate().compareTo(t2.getPurchaseDate()) * -1;
//    }
//} else if (t1.getPurchaseDate() != null) {
//    if(t2.getRegDate() != null) {
//        return t1.getPurchaseDate().compareTo(t2.getRegDate()) * -1;
//    } else if(t2.getRefundSubmitDate() != null) {
//        return t1.getPurchaseDate().compareTo(t2.getRefundSubmitDate()) * -1;
//    } else if (t2.getPurchaseDate() != null) {
//        return t1.getPurchaseDate().compareTo(t2.getPurchaseDate()) * -1;
//    }
//}
//return 0;
//});


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
