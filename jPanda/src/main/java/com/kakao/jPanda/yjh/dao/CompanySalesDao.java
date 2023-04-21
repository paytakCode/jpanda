package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;

public interface CompanySalesDao {
	List<CompanySalesDto> selectCompanySalesByYears(CompanySalesDto companySalesDto);
}
