package com.kakao.jPanda.lhw.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.BambooUseDto;
import com.kakao.jPanda.lhw.domain.ReportDto;
import com.kakao.jPanda.lhw.domain.ReviewDto;
import com.kakao.jPanda.lhw.domain.TalentDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	private final TalentDao talentDao;
	
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
	
	// 리뷰 인서트
	@Override
	public int addReview(ReviewDto review) {
		return talentDao.insertReview(review);
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

	// 재능 삭제(업데이트)
	@Override
	public int updateTalentStatus(Long talentNo) {
		return talentDao.updateTalent(talentNo);
	}
	
	// 리뷰 인서트 검증
	@Override
	public List<BambooUseDto> findBambooUseListByTalentNo(Long talentNo) {
		return talentDao.selectBambooUseListByTalentNo(talentNo);
	}
	
	// 재능 구매자 정보 인서트
	@Override
	public int addBambooUse(BambooUseDto bambooUse, Long totalBamboo) {
		int result = 2;
		
		if(isBuyBefore(bambooUse) == 1) {
			result = -1; // 구매한 적이 있으면 -1을 리턴
		} else if (totalBamboo == null || totalBamboo < bambooUse.getUseBamboo()) {
			result = 0; // 포인트 잔액 검증 후 포인트 부족시 0 을 리턴 
		} else {
			result = talentDao.insertBambooUse(bambooUse); // 인서트 성공
		}
		return result;
	}
	
	// 구매 여부 확인 용도
	private int isBuyBefore(BambooUseDto bambooUse) {
		if(bambooUse == null) {
			return 0;
		}
		return talentDao.selectBuyCheckByBambooUse(bambooUse);
	}

	// 신고하기 인서트
	@Override
	public String addReport(ReportDto report) {
		System.out.println("addReport -> " + report);
	    // 중복 신고 검증용
	    int reportCheck = talentDao.selectReportCheck(report);
	    System.out.println("reportCheck -> " + reportCheck);
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
