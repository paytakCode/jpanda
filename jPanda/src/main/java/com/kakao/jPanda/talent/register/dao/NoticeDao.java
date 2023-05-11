package com.kakao.jPanda.talent.register.dao;

import java.util.List;

import com.kakao.jPanda.main.domain.NoticeDto;
import com.kakao.jPanda.main.domain.PagerDto;

public interface NoticeDao {

	int 			selectNoticeCountByPager(PagerDto pager);
	List<NoticeDto> 	selectNoticesByPager(PagerDto pager);
	NoticeDto 			selectNoticeByNoticeNo(Long noticeNo);

}
