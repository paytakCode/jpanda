package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Talent;

public interface FiltersService {
	
	List<Talent> findTalentListByUpperCategoryAndFilter(Filters filters);
}
