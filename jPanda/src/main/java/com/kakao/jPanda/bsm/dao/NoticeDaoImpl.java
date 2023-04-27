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
		return session.selectOne("selectNoticeCountByPager", pager);
	}

	@Override
	public List<Notice> selectNoticesByPager(Pager pager) {
		return session.selectList("selectNoticesByPager", pager);
	}

}
