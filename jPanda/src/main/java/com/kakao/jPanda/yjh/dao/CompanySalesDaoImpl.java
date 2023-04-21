package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanySalesDaoImpl implements CompanySalesDao {
	private final SqlSession sqlSession;

	@Override
	public List<CompanySalesDto> selectCompanySalesByYears(CompanySalesDto companySalesDto) {
		List<CompanySalesDto> csList = sqlSession.selectList("selectCompanySalesByYears", companySalesDto);
		System.out.println("repository");
		return csList;
	}

}
