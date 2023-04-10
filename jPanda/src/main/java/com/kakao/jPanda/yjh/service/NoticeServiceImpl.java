package com.kakao.jPanda.yjh.service;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.yjh.dao.NoticeDao;
import com.kakao.jPanda.yjh.domain.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;
	
	@Override
	public void insertNotice(Notice notice) {
		System.out.println("=====notice service insertNotice() start=====");
		noticeDao.insertNotice(notice);

	}

}
