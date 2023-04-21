package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Talent;

public interface FiltersDao {

	List<Talent> selectTalentListByUpperCategoryAndFilter(Filters filters);

}
