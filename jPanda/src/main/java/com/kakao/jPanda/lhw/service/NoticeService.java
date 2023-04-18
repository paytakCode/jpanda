package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Notice;

public interface NoticeService {
	
	// 공지사항 전체 리스트
		List<Notice> findNoticeList();

}
