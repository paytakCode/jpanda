package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.NoticeDao;
import com.kakao.jPanda.lhw.domain.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeDao noticeDao;

	@Override
	public List<Notice> findNoticeList() {
		return noticeDao.selectNoticeList();
	}

}
