package com.kakao.jPanda.bsm.dao;

import java.util.List;

import com.kakao.jPanda.bsm.domain.Notice;
import com.kakao.jPanda.bsm.domain.Pager;

public interface NoticeDao {

	int 			selectNoticeCountByPager(Pager pager);
	List<Notice> 	selectNoticesByPager(Pager pager);
	Notice 			selectNoticeByNoticeNo(Long noticeNo);

}
