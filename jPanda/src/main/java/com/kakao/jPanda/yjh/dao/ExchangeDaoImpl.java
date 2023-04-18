package com.kakao.jPanda.yjh.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.kakao.jPanda.yjh.domain.Exchange;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExchangeDaoImpl implements ExchangeDao {
	private final SqlSession sqlSession;
	
	@Override
	public List<Exchange> selectExchange() {
		System.out.println("===== Exchangerepository selectExchangeByStatus() start =====");
		List<Exchange> exList = null;
		try {
			exList = sqlSession.selectList("selectExchange");
		} catch(Exception e) {
			System.out.println("Exchangerepository selectExchangeByStatus() exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return exList;
	}

	@Override
	public Exchange selectExchangeByExchangeNo(Long exchangeNo) {
		Exchange exchange = null;
		
		try {
			exchange = sqlSession.selectOne("selectExchangeByExchangeNo", exchangeNo);
			
		} catch(Exception e) {
			System.out.println("ExchangeRepository selectExchangeByExchangeNo Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return exchange;
	}

	@Override
	public void updateExchange(Exchange exchange) {
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

}
