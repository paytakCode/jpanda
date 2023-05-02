package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.CouponDto;
import com.kakao.jPanda.yjh.domain.ExchangeDto;
import com.kakao.jPanda.yjh.domain.NoticeDto;
import com.kakao.jPanda.yjh.domain.TalentDto;
import com.kakao.jPanda.yjh.domain.TalentRefundDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
	private final SqlSession sqlSession;
	
	//notice
	@Override
	public List<NoticeDto> selectNotice() {
		log.info("Dao selectNotice() start");
		List<NoticeDto> noticeList = sqlSession.selectList("selectNotice");
		
		return noticeList;
	}
	
	@Override
	public int insertNotice(NoticeDto notice) {
		log.info("Dao insertNotice() start");
		int result = sqlSession.insert("insertNotice", notice);
		
		return result;
	}
	
	@Override
	public NoticeDto selectNoticeByNoticeNo(Long noticeNo) {
		log.info("Dao selectNoticeByNoticeNo() start");
		NoticeDto notice = sqlSession.selectOne("selectNoticeByNoticeNo", noticeNo);
		
		return notice;
	}
	
	@Override
	public int updateNotice(NoticeDto notice) {
		log.info("Dao updateNotice() start");
		int result = sqlSession.update("updateNotice", notice);
		
		return result;
	}
	
	//exchange
	@Override
	public ExchangeDto selectExchangeByExchangeNo(Long exchangeNo) {
		log.info("Dao selectExchangeByExchangeNo() start");
		ExchangeDto exchange = null;
		
		try {
			exchange = sqlSession.selectOne("selectExchangeByExchangeNo", exchangeNo);
			
		} catch(Exception e) {
			log.debug("Dao selectExchangeByExchangeNo() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return exchange;
	}
	
	@Override
	public List<ExchangeDto> selectExchange() {
		log.info("Dao selectExchange() start");
		List<ExchangeDto> exList = null;
		
		try {
			exList = sqlSession.selectList("selectExchange");
		} catch(Exception e) {
			
			log.info("Dao selectExchange() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return exList;
	}
	
	@Override
	public int updateExchange(ExchangeDto exchange) {
		log.info("Dao updateExchange() start");
		return sqlSession.update("updateExchange", exchange);
	}
	
	//coupon
	@Override
	public List<CouponDto> selectCouponsExpired() {
		log.info("Coupon Dao selectCouponsExpired() start");
		List<CouponDto> couponList = null;
		
		try {
			couponList = sqlSession.selectList("selectCouponsExpired");
			
		} catch(Exception e) {
			log.info("Coupon Dao selectCouponsExpired() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return couponList;
	}

	@Override
	public List<CouponDto> findCouponList() {
		log.info("Dao findCouponList() start");
		List<CouponDto> couponList = null;
		
		try {
			couponList = sqlSession.selectList("findCouponList");
			
		} catch(Exception e) {
			log.info("Dao findCouponList() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return couponList;
	}
	
	@Override
	public int insertCoupon(CouponDto couponDto) {
		log.info("Dao insertCoupon() start");
		log.info("Dao couponDto : "+couponDto.toString());
		
		return sqlSession.insert("insertCoupon", couponDto);
	}
	
	//company-sales
	@Override
	public List<CompanySalesDto> selectCompanySalesByYYYYDate(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByYYYYDate() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanySalesByYYYYDate", companySalesDto);
		
		return csList;
	}
	
	@Override
	public List<CompanySalesDto> selectCompanySalesByMMDate(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByMMDate() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanySalesByMMDate", companySalesDto);
		
		return csList;
	}
	
	@Override
	public List<CompanySalesDto> selectCompanySalesByDDDate(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByDDDate() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanyByDDDate", companySalesDto);
		
		return csList;
	}
	
	//talent
	@Override
	public List<TalentDto> selectTalent() {
		log.info("Dao selectTalent() start");
		
		return sqlSession.selectList("selectTalent");
	}

	@Override
	public int updateTalentByTalentNos(TalentDto talentDto) {
		int returnValue = 0;
		
		try {
			returnValue = sqlSession.update("updateTalentByTalentNos", talentDto);
		} catch(Exception e) {
			log.info("Dao updateTalentBySellerIds() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return returnValue;
	}

	@Override
	public List<TalentRefundDto> selectTalentRefund() {
		log.info("TalentRefund Dao selectTalentRefund() start");
		List<TalentRefundDto> refundList = null;
		try {
			refundList = sqlSession.selectList("selectTalentRefund");
			
		} catch(Exception e) {
			log.info("TalentRefund Dao selectTalentRefun() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return refundList;
	}

	@Override
	public int updateTalentRefundToSuccessByPurchaseNosAndStatus(TalentRefundDto paramDto) {
		log.info("TalentRefund Dao updateTalentRefundToSuccessByPurchaseNosAndStatus() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateTalentRefundToSuccessByPurchaseNosAndStatus", paramDto);
			
		} catch(Exception e) {
			log.info("TalentRefund Dao updateTalentRefundToSuccessByPurchaseNosAndStatus() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateTalentRefundToCompanionByPurchaseNosAndStatus(TalentRefundDto paramDto) {
		log.info("TalentRefund Dao updateTalentRefundToCompanionByPurchaseNosAndStarus() start");
		int result = 0;
		
		try {
			result = sqlSession.update("updateTalentRefundToCompanionByPurchaseNosAndStatus", paramDto);
		} catch(Exception e) {
			log.info("TalentRefund Dao updateTalentRefundToCompanionByPurchaseNosAndStatus() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

}
