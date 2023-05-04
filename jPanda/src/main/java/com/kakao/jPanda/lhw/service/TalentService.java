package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.BambooUseDto;
import com.kakao.jPanda.lhw.domain.ReportDto;
import com.kakao.jPanda.lhw.domain.ReviewDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

public interface TalentService {
	
	// 재능 상세 페이지 
	TalentDto findBoardTalentByTalentNo(Long talentNo);
	
	// 리뷰 리스트 불러오기
	List<ReviewDto> findReviewListByTalentNo(Long talentNo);
	
	// 리뷰 인서트
	int addReview(ReviewDto review);
	
	// 리뷰 업데이트
	int modifyReview(ReviewDto review);
	
	// 리뷰 삭제
	int removeReview(ReviewDto review);
	
	// 재능 삭제 (업데이트)
	int updateTalentStatus(Long talentNo);
	
	// 리뷰 인서트 검증
	List<BambooUseDto> findBambooUseListByTalentNo(Long talentNo);

	// 재능 구매자 정보 인서트
	int addBambooUse(BambooUseDto bambooUse, Long totalBamboo);

	// 신고 인서트
	String addReport(ReportDto report);

	// 뷰 카운트
	int modifyTalentToViewCount(Long talentNo);

	
	

	
	
}
