package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.TalentDao;
import com.kakao.jPanda.lhw.domain.Review;
import com.kakao.jPanda.lhw.domain.Talent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {
	private final TalentDao talentDao;
	
	// 재능 상세 페이지 
	@Override
	public Talent findTalentByTalentNo(Long talentNo) {
		return talentDao.selectTalentByTalentNo(talentNo);
	}
	
	// 리뷰 리스트 불러오기
	@Override
	public List<Review> findReviewListByTalentNo(Long talentNo) {
		return talentDao.selectReivewListByTalentNo(talentNo);
	}
	
	// 리뷰 인서트
	@Override
	public int addReview(Review review) {
		return talentDao.insertReview(review);
	}

	@Override
	public int modifyReview(Review review) {
		return talentDao.updateReview(review);
	}

	@Override
	public int removeReview(Review review) {
		return talentDao.deleteReview(review);
	}
}
