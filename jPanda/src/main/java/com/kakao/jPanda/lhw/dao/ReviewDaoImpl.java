package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Review;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {
	
	private final SqlSession sqlSession;
	
	@Override
	public List<Review> selectReivewListByTalentNo(Long talentNo) {
		return sqlSession.selectList("selectReviewListByTalentNo", talentNo);
	}

}
