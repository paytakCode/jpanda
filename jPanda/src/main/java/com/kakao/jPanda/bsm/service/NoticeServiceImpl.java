package com.kakao.jPanda.bsm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.bsm.dao.NoticeDao;
import com.kakao.jPanda.bsm.domain.Notice;
import com.kakao.jPanda.bsm.domain.Pager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;
	
	@Override
	public int findNoticeCountByPager(Pager pager) {
		return noticeDao.selectNoticeCountByPager(pager);
	}

	@Override
	public List<Notice> findNoticeListByPager(Pager pager) {
		return noticeDao.selectNoticesByPager(pager);
	}

}
