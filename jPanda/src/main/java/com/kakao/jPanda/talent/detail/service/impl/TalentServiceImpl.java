package com.kakao.jPanda.talent.detail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.kakao.jPanda.kyg.service.ChargeService;
import com.kakao.jPanda.talent.detail.dao.TalentDao;
import com.kakao.jPanda.talent.detail.domain.BambooUseDto;
import com.kakao.jPanda.talent.detail.domain.ReportDto;
import com.kakao.jPanda.talent.detail.domain.ReviewDto;
import com.kakao.jPanda.talent.detail.domain.TalentDto;
import com.kakao.jPanda.talent.detail.service.TalentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	private final TalentDao talentDao;
	private final ChargeService chargeService;
	
	// 재능 상세 페이지 
	@Override
	public TalentDto findBoardTalentByTalentNo(Long talentNo) {
		return talentDao.selectBoardTalentByTalentNo(talentNo);
	}
	
	// 리뷰 리스트 불러오기
	@Override
	public List<ReviewDto> findReviewListByTalentNo(Long talentNo) {
		return talentDao.selectReivewListByTalentNo(talentNo);
	}
	
	// 리뷰 업데이트
	@Override
	public int modifyReview(ReviewDto review) {
		return talentDao.updateReview(review);
	}
	
	// 리뷰 삭제
	@Override
	public int removeReview(ReviewDto review) {
		return talentDao.deleteReview(review);
	}

	// 재능 판매종료(업데이트)
	@Override
	public int updateTalentStatus(Long talentNo) {
		return talentDao.updateTalent(talentNo);
	}
	
	// 리뷰 인서트
	@Override
	public int addReview(ReviewDto review) {
		String talentNo = review.getTalentNo().toString();
		String reviewerId = review.getReviewerId();
		int result = 0;
		Map<String, Object> selectBambooUseByTalentNoAndBuyerId = new HashMap<String, Object>();
		
		selectBambooUseByTalentNoAndBuyerId.put("talentNo", talentNo);
		selectBambooUseByTalentNoAndBuyerId.put("buyerId", reviewerId);
		
		// 리뷰 중복 인서트 검증 및 구매 검증
		if (!talentDao.selectReviewInsertCheck(review).isEmpty()) {
			result = -1;
		} else if (!talentDao.selectBambooUseByTalentNoAndBuyerId(selectBambooUseByTalentNoAndBuyerId).isEmpty() ) {
			result = talentDao.insertReview(review);
		} 
		
		log.info("ReviewInsertCheck -> " + result);
		
		return result;
	}
	
	// 재능 구매자 정보 인서트
	@Override
	public int addBambooUse(BambooUseDto bambooUse) {
		String memberId = bambooUse.getBuyerId();
		// 영광씨 토탈 밤부 가져와서 bambooUse 객체에 저장
		Long totalBamboo = chargeService.findTotalBambooByMemberId(memberId);
		bambooUse.setTotalBamboo(totalBamboo);
		log.info("totalBamboo -> " + totalBamboo);
		
		int result = 0;
		
		if(isItBuyBefore(bambooUse) == 1) {
			result = -1; // 구매한 적이 있으면 -1을 리턴
		} else if (totalBamboo == null || totalBamboo < bambooUse.getUseBamboo()) {
			result = 0; // 포인트 잔액 검증 후 포인트 부족시 0 을 리턴 
		} else {
			result = talentDao.insertBambooUse(bambooUse); // 인서트 성공
		}
		return result;
	}
	
	// 중복 구매 여부 검증
	private int isItBuyBefore(BambooUseDto bambooUse) {
		if(bambooUse == null) {
			return 0;
		}
		return talentDao.selectBuyCheckByBambooUse(bambooUse);
	}

	// 신고하기 인서트
	@Override
	public String addReport(ReportDto report) {
	    // 중복 신고 검증용
	    int reportCheck = talentDao.selectReportCheck(report);
	    // 중복 신고 검증
	    if (reportCheck > 0) {
	        return "<script> alert('중복 신고는 불가능 합니다. 빠른 시일 내에 처리 하겠습니다. 감사합니다.'); history.back(); </script>";
	    } else {
	    	// 인서트
	    	int insertResult = talentDao.insertReport(report);
	    	if (insertResult > 0) {
	    		return "<script> alert('신고가 접수되었습니다. 감사합니다.'); history.back(); </script>";
	    	} else {
	    		return "<script> alert('신고 접수에 실패했습니다. 잠시 후 다시 시도해주세요.'); history.back(); </script>";
	    	}
	    }
	}
	
	// 뷰 카운트 업데이트
	@Override
	public int modifyTalentToViewCount(Long talentNo) {
		return talentDao.updateTalentToViewCount(talentNo);
	}
	
}
