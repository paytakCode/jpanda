package com.kakao.jPanda.bsm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.bsm.domain.NoticeDto;
import com.kakao.jPanda.bsm.domain.PagerDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao {
	private final SqlSession session;
	
	@Override
	public int selectNoticeCountByPager(PagerDto pager) {
		int result = 0;
		try {
			result = session.selectOne("selectNoticeCountByPager", pager);
		} catch (Exception e) {
			log.error("NoticeDaoImpl selectNoticeCountByPager e.getMessage() ->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<NoticeDto> selectNoticesByPager(PagerDto pager) {
		List<NoticeDto> noticeList = null;
		try {
			noticeList = session.selectList("selectNoticesByPager", pager);
		} catch (Exception e) {
			log.error("NoticeDaoImpl selectNoticesByPager e.getMessage() ->" + e.getMessage());
		}
		return noticeList;
	}

	@Override
	public NoticeDto selectNoticeByNoticeNo(Long noticeNo) {
		NoticeDto notice = null;
		try {
			notice = session.selectOne("selectRegistNoticeByNoticeNo", noticeNo);
		} catch (Exception e) {
			log.error("NoticeDaoImpl selectNoticeByNoticeNo e.getMessage() ->" + e.getMessage());
		}
		return notice;
	}

}
