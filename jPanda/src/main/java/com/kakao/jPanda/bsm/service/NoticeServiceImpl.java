package com.kakao.jPanda.bsm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.bsm.dao.NoticeDao;
import com.kakao.jPanda.bsm.domain.NoticeDto;
import com.kakao.jPanda.bsm.domain.PagerDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;
	
	@Override
	public Map<String, Object> findNoticeCountByPager(PagerDto pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		// service로 옮김
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
