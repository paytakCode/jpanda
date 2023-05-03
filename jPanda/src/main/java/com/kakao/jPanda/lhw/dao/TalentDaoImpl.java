package com.kakao.jPanda.lhw.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.lhw.domain.BambooUseDto;
import com.kakao.jPanda.lhw.domain.ReviewDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TalentDaoImpl implements TalentDao {
	
	private final SqlSession sqlSession;
	
	// 재능 판매 상세 페이지 불러오기
	@Override
	public TalentDto selectBoardTalentByTalentNo(Long talentNo) {
		return sqlSession.selectOne("selectBoardTalentByTalentNo", talentNo);
	}
	
	// 리뷰 리스트 불러오기
	@Override
	public List<ReviewDto> selectReivewListByTalentNo(Long talentNo) {
		return sqlSession.selectList("selectReviewListByTalentNo", talentNo);
	}
	
	// 리뷰 인서트
	@Override
	public int insertReview(ReviewDto review) {
		return sqlSession.insert("insertReview", review);
	}
	
	// 리뷰 업데이트
	@Override
	public int updateReview(ReviewDto review) {
		return sqlSession.update("updateReview", review);
	}
	
	// 리뷰 삭제
	@Override
	public int deleteReview(ReviewDto review) {
		return sqlSession.delete("deleteReview", review);
	}

	// 재능 삭제 (업데이트)
	@Override
	public int updateTalent(Long talentNo) {
		return sqlSession.update("updateTalent", talentNo);
	}

	// 리뷰 인서트 검증용
	@Override
	public List<BambooUseDto> selectBambooUseListByTalentNo(Long talentNo) {
		return sqlSession.selectList("selectBambooUseListByTalentNo", talentNo);
	}
	
}
