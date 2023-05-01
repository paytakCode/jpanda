package com.kakao.jPanda.bsm.service;

import java.util.Map;

import com.kakao.jPanda.bsm.domain.Notice;
import com.kakao.jPanda.bsm.domain.Pager;

public interface NoticeService {
	Map<String, Object> 	findNoticeCountByPager(Pager pager);
	Notice 					findNoticeByNoticeNo(Long noticeNo);
}
