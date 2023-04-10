package com.kakao.jPanda.yjh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.yjh.domain.Notice;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeDaoImpl implements NoticeDao {
	private final SqlSession sqlSession;
	
	@Override
	public void insertNotice(Notice notice) {
		System.out.println("=====notice repository insertNorice() start=====");
		sqlSession.insert("noticeInsert", notice);

	}

}
