package com.kakao.jPanda.yjh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.CompanySalesDao;
import com.kakao.jPanda.yjh.domain.CompanySalesDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanySalesServiceImpl implements CompanySalesService {
	private final CompanySalesDao companySalesDao;

	@Override
	public List<CompanySalesDto> findCompanySalesByYears(CompanySalesDto companySalesDto) {
		List<CompanySalesDto> csList = companySalesDao.selectCompanySalesByYears(companySalesDto);
		System.out.println("service");
		return csList;
	}

}
