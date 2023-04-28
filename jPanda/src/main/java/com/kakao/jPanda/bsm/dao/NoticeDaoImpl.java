package com.kakao.jPanda.bsm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.bsm.domain.Notice;
import com.kakao.jPanda.bsm.domain.Pager;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao {
	private final SqlSession session;
	
	@Override
	public int selectNoticeCountByPager(Pager pager) {
		int result = 0;
		try {
			result = session.selectOne("selectNoticeCountByPager", pager);
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl selectNoticeCountByPager e.getMessage() ->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<Notice> selectNoticesByPager(Pager pager) {
		List<Notice> noticeList = null;
		try {
			noticeList = session.selectList("selectNoticesByPager", pager);
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl selectNoticesByPager e.getMessage() ->" + e.getMessage());
		}
		return noticeList;
	}

}
