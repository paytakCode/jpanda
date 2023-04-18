package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Notice;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao {
	
	private final SqlSession sqlSession;
	
	@Override
	public List<Notice> selectNoticeList() {
		return sqlSession.selectList("selectNoticeList");
	}
}
