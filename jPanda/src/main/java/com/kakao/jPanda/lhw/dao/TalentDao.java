package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Review;
import com.kakao.jPanda.lhw.domain.Talent;

public interface TalentDao {

	// 재능 판매 상세 페이지 불러오기
	Talent selectTalentByTalentNo(Long talentNo);
	
	// 리뷰 리스트 불러오기
	List<Review> selectReivewListByTalentNo(Long talentNo);
	
	// 리뷰 인서트
	int insertReview(Review review);
	
	// 리뷰 업데이트
	int updateReview(Review review);
	
	// 리뷰 삭제
	int deleteReview(Review review);
	
	
	
}
