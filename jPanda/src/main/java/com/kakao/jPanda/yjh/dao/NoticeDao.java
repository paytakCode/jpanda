package com.kakao.jPanda.yjh.dao;

import java.util.List;

import com.kakao.jPanda.yjh.domain.Notice;

public interface NoticeDao {
	int insertNotice(Notice notice);
	List<Notice> selectNotice();
	Notice selectNoticeByNoticeNo(Long noticeNo);
	int updateNotice(Notice notice);
}
