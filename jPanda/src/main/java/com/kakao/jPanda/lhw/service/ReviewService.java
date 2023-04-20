package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Review;

public interface ReviewService {
	
	List<Review> findReviewListByTalentNo(Long talentNo);
	
}
