package com.kakao.jPanda.bsm.service;

import java.util.List;

import com.kakao.jPanda.bsm.domain.Notice;
import com.kakao.jPanda.bsm.domain.Pager;

public interface NoticeService {
	int 			findNoticeCountByPager(Pager pager);
	List<Notice> 	findNoticeListByPager(Pager pager);
}
