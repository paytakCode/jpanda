package com.kakao.jPanda.bsm.service;

import java.util.Map;

import com.kakao.jPanda.bsm.domain.NoticeDto;
import com.kakao.jPanda.bsm.domain.PagerDto;

public interface NoticeService {
	Map<String, Object> 	findNoticeCountByPager(PagerDto pager);
	NoticeDto 					findNoticeByNoticeNo(Long noticeNo);
}
