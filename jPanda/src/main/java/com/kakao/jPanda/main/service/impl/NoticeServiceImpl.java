package com.kakao.jPanda.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.main.domain.NoticeDto;
import com.kakao.jPanda.main.domain.PagerDto;
import com.kakao.jPanda.main.service.NoticeService;
import com.kakao.jPanda.talent.register.dao.NoticeDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;
	
	@Override
	public Map<String, Object> findNoticeCountByPager(PagerDto pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		int totalCount = noticeDao.selectNoticeCountByPager(pager);
		pager.setTotalCount(totalCount);
		List<NoticeDto> noticeList = findNoticeListByPager(pager);
		
		map.put("noticeList", noticeList);
		map.put("pager", pager);
		
		return map;
	}

	private List<NoticeDto> findNoticeListByPager(PagerDto pager) {
		return noticeDao.selectNoticesByPager(pager);
	}

	@Override
	public NoticeDto findNoticeByNoticeNo(Long noticeNo) {
		
		return noticeDao.selectNoticeByNoticeNo(noticeNo);
	}

}
