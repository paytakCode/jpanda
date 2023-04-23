package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;
import com.kakao.jPanda.yjh.domain.Coupon;
import com.kakao.jPanda.yjh.domain.Exchange;
import com.kakao.jPanda.yjh.domain.Notice;
import com.kakao.jPanda.yjh.domain.Talent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
	private final SqlSession sqlSession;
	
	//notice
	@Override
	public List<Notice> selectNotice() {
		log.info("Dao selectNotice() start");
		List<Notice> noticeList = sqlSession.selectList("selectNotice");
		
		return noticeList;
	}
	
	@Override
	public int insertNotice(Notice notice) {
		log.info("Dao insertNotice() start");
		int result = sqlSession.insert("insertNotice", notice);
		
		return result;
	}
	
	@Override
	public Notice selectNoticeByNoticeNo(Long noticeNo) {
		log.info("Dao selectNoticeByNoticeNo() start");
		Notice notice = sqlSession.selectOne("selectNoticeByNoticeNo", noticeNo);
		
		return notice;
	}
	
	@Override
	public int updateNotice(Notice notice) {
		log.info("Dao updateNotice() start");
		int result = sqlSession.update("updateNotice", notice);
		
		return result;
	}
	
	//exchange
	@Override
	public Exchange selectExchangeByExchangeNo(Long exchangeNo) {
		log.info("Dao selectExchangeByExchangeNo() start");
		Exchange exchange = null;
		
		try {
			exchange = sqlSession.selectOne("selectExchangeByExchangeNo", exchangeNo);
			
		} catch(Exception e) {
			log.debug("Dao selectExchangeByExchangeNo() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return exchange;
	}
	
	@Override
	public List<Exchange> selectExchange() {
		log.info("Dao selectExchange() start");
		List<Exchange> exList = null;
		
		try {
			exList = sqlSession.selectList("selectExchange");
		} catch(Exception e) {
			
			log.info("Dao selectExchange() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return exList;
	}
	
	@Override
	public void updateExchange(Exchange exchange) {
		log.info("Dao updateExchange() start");
		sqlSession.update("updateExchange", exchange);
	}
	
//	@Override
//	public void updateExchangeByExchangeNos(List<Long> listExchangeNo) {
//		System.out.println("===== ExchangeRepository updateExchangeByExchangeNos() start =====");
//		System.out.println(listExchangeNo.toString());
//		
//		try {
//			sqlSession.update("updateExchangeByExchangeNos", listExchangeNo);
//			
//		} catch(Exception e) {
//			System.out.println("Exchangerepository updateExchangeByExchangeNos() exception : "+e.getMessage());
//			e.printStackTrace();
//		}
//		
//	}
//
//	@Override
//	public void updateExchangeByExchangeNosToCompanion(List<Long> listExchangeNo) {
//		System.out.println("===== ExchangeRepository updateExchangeByExchangeNosToCompanion() start =====");
//		try {
//			sqlSession.update("updateExchangeByExchangeNosToCompanion", listExchangeNo);
//			
//		} catch(Exception e) {
//			System.out.println("Exchangerepository updateExchangeByExchangeNosToCompanion() exception : "+e.getMessage());
//			e.printStackTrace();
//		}
//		
//	}
	
	//coupon
	@Override
	public List<Coupon> findCouponList() {
		log.info("Dao findCouponList() start");
		List<Coupon> couponList = null;
		
		try {
			couponList = sqlSession.selectList("findCouponList");
			
		} catch(Exception e) {
			log.info("Dao findCouponList() Exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return couponList;
	}
	
	@Override
	public void insertCoupon(Coupon coupon) {
		log.info("Dao insertCoupon() start");
		sqlSession.insert("insertCoupon", coupon);
	}
	
	//company-sales
	@Override
	public List<CompanySalesDto> selectCompanySalesByYears(CompanySalesDto companySalesDto) {
		log.info("Dao selectCompanySalesByYears() start");
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanySalesByYears", companySalesDto);
		
		return csList;
	}
	
	
	//talent
	@Override
	public List<Talent> selectTalent() {
		log.info("Dao selectTalent() start");
		
		return sqlSession.selectList("selectTalent");
	}

}
