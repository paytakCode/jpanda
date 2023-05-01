package com.kakao.jPanda.bsm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> findNoticeCountByPager(Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		// service로 옮김
		int totalCount = noticeDao.selectNoticeCountByPager(pager);
		pager.setTotalCount(totalCount);
		System.out.println("totalCount - " + totalCount);
		System.out.println("pager.getSearch() - " + pager.getSearch());
		System.out.println("pager.getCurrentPage() - " + pager.getCurrentPage());
		System.out.println("pager - " + pager);
		
		List<Notice> noticeList = findNoticeListByPager(pager);
		
		map.put("noticeList", noticeList);
		map.put("pager", pager);
		
		return map;
	}

	private List<Notice> findNoticeListByPager(Pager pager) {
		return noticeDao.selectNoticesByPager(pager);
	}

	@Override
	public Notice findNoticeByNoticeNo(Long noticeNo) {
		
		return noticeDao.selectNoticeByNoticeNo(noticeNo);
	}

}
