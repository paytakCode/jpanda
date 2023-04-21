package com.kakao.jPanda.yjh.service;

import java.util.List;

import com.kakao.jPanda.yjh.domain.CompanySalesDto;

public interface CompanySalesService {
	List<CompanySalesDto> findCompanySalesByYears(CompanySalesDto companySalesDto);
}
