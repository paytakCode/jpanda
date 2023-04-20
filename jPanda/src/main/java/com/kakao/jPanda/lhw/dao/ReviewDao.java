package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Review;

public interface ReviewDao {

	List<Review> selectReivewListByTalentNo(Long talentNo);

}
