package com.kakao.jPanda.lhw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.Review;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TalentDaoImpl implements TalentDao {
	
	private final SqlSession sqlSession;
	
	// 재능 판매 상세 페이지 불러오기
	@Override
	public Talent selectTalentByTalentNo(Long talentNo) {
		return sqlSession.selectOne("selectTalentByTalentNo", talentNo);
	}
	
	// 리뷰 리스트 불러오기
	@Override
	public List<Review> selectReivewListByTalentNo(Long talentNo) {
		return sqlSession.selectList("selectReviewListByTalentNo", talentNo);
	}

	@Override
	public int insertReview(Review review) {
		return sqlSession.insert("insertReview", review);
	}
	

}
