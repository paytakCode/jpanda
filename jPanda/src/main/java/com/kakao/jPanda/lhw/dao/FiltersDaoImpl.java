package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FiltersDaoImpl implements FiltersDao {
	
	private final SqlSession sqlSession;
	
	@Override
	public List<Talent> selectTalentListByUpperCategoryAndFilter(Filters filter) {
		return sqlSession.selectList("selectTalentListByUpperCategoryAndFilters", filter);
	}

}
