package com.kakao.jPanda.yjh.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao {
	private final SqlSession sqlSession;
	
	@Override
	public int insertNotice(Notice notice) {
		System.out.println("===== NoticeRepository insertNorice() start =====");
		int result = sqlSession.insert("insertNotice", notice);
		
		return result;
	}

	@Override
	public List<Notice> selectNotice() {
		log.info("===== NoticeRepository selectNotice start =====");
		List<Notice> noticeList = sqlSession.selectList("selectNotice");
		
		return noticeList;
	}

	@Override
	public Notice selectNoticeByNoticeNo(Long noticeNo) {
		System.out.println("===== NoticeRepository selectNoticeByNoticeNo start =====");
		Notice notice = sqlSession.selectOne("selectNoticeByNoticeNo", noticeNo);
		
		return notice;
	}

	@Override
	public int updateNotice(Notice notice) {
		System.out.println("===== NoticeRepository updateNotice() start =====");
		int result = sqlSession.update("updateNotice", notice);
		
		return result;
	}

}
