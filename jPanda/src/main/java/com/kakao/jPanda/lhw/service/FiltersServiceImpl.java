package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.FiltersDao;
import com.kakao.jPanda.lhw.domain.Filters;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FiltersServiceImpl implements FiltersService {
	
	private final FiltersDao filtersDao;
	
	public List<Talent> findTalentListByUpperCategoryAndFilter(Filters filters){
		return filtersDao.selectTalentListByUpperCategoryAndFilter(filters);
	}
	
}
