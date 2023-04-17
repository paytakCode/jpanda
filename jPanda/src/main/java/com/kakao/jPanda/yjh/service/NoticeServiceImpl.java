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
	public void addNotice(Notice notice) {
		System.out.println("===== NoticeService addNotice() start =====");
		noticeDao.insertNotice(notice);

	}

}
