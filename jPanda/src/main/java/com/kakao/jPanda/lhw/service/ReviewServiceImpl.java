package com.kakao.jPanda.lhw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.jPanda.lhw.dao.ReviewDao;
import com.kakao.jPanda.lhw.domain.Review;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewDao reviewDao;
	
	@Override
	public List<Review> findReviewListByTalentNo(Long talentNo) {
		return reviewDao.selectReivewListByTalentNo(talentNo);
	}
}
