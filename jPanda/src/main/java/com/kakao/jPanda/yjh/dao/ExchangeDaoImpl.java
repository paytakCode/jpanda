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
	public List<Exchange> exchangedList(Exchange exchange) {
		System.out.println("===== Exchange repository exchangedList start =====");
		List<Exchange> exList = null;
		try {
			exList = sqlSession.selectList("ExchangedList", exchange);
		} catch(Exception e) {
			System.out.println("Exchange repository exchangedList exception : "+e.getMessage());
			e.printStackTrace();
		}
		
		return exList;
	}

	@Override
	public void exchangedUpdate(Exchange exchange) {
		System.out.println("===== Exchange repository exchangedUpdate() start =====");
		sqlSession.update("ExchangedUpdate", exchange);
	}

}
