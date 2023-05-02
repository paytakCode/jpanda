package com.kakao.jPanda.bsm.dao;

import java.util.List;

import com.kakao.jPanda.bsm.domain.NoticeDto;
import com.kakao.jPanda.bsm.domain.PagerDto;

public interface NoticeDao {

	int 			selectNoticeCountByPager(PagerDto pager);
	List<NoticeDto> 	selectNoticesByPager(PagerDto pager);
	NoticeDto 			selectNoticeByNoticeNo(Long noticeNo);

}
