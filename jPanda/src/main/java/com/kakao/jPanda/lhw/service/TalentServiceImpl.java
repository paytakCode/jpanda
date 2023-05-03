package com.kakao.jPanda.lhw.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.BambooChargeDto;
import com.kakao.jPanda.lhw.domain.BambooUseDto;
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
	public int addBambooUseList(BambooUseDto bambooUse) {
		return talentDao.insertBambooUse(bambooUse);
	}
	
	// 재능 구매자 잔여 포인트 조회
	@Override
	public List<BambooChargeDto> findChargeBambooByByuerId(String buyerId) {
		return talentDao.selectChargeBambooByByuerId(buyerId);
	}

}
