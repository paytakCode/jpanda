package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.BambooUseDto;
import com.kakao.jPanda.lhw.domain.ReportDto;
import com.kakao.jPanda.lhw.domain.ReviewDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

public interface TalentDao {

	// 재능 판매 상세 페이지 불러오기
	TalentDto selectBoardTalentByTalentNo(Long talentNo);
	
	// 리뷰 리스트 불러오기
	List<ReviewDto> selectReivewListByTalentNo(Long talentNo);
	
	// 리뷰 인서트
	int insertReview(ReviewDto review);
	
	// 리뷰 업데이트
	int updateReview(ReviewDto review);
	
	// 리뷰 삭제
	int deleteReview(ReviewDto review);

	// 재능 삭제(업데이트)
	int updateTalent(Long talentNo);

	// 리뷰 인서트 검증용
	List<BambooUseDto> selectBambooUseListByTalentNo(Long talentNo);

	// 재능 구매자 정보 인서트
	int insertBambooUse(BambooUseDto bambooUse);

	// 구매 여부 확인 용도
	int selectBuyCheckByBambooUse(BambooUseDto bambooUse);

	// 신고 여부 검증용
	List<ReportDto> selectReportByReportId(String reportId);
	
	// 신고 인서트
	int insertReport(ReportDto report);

	// 뷰 카운트 업데이트
	int updateTalentToViewCount(Long talentNo);
	
	
	
}
