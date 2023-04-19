package com.kakao.jPanda.yjh.service;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Notice;

public interface NoticeService {
	String addNotice(Notice notice);
	List<Notice> findNotice();
	Notice findNoticeByNoticeNo(String noticeNo);
	String modifyNotice(Notice notice);
}
