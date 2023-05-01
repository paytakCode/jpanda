package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Review;
import com.kakao.jPanda.lhw.domain.Talent;

public interface TalentService {
	
	// 재능 상세 페이지 
	Talent findBoardTalentByTalentNo(Long talentNo);
	
	// 리뷰 리스트 불러오기
	List<Review> findReviewListByTalentNo(Long talentNo);
	
	// 리뷰 인서트
	int addReview(Review review);
	
	// 리뷰 업데이트
	int modifyReview(Review review);
	
	// 리뷰 삭제
	int removeReview(Review review);
}
