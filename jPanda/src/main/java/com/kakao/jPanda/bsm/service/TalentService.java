package com.kakao.jPanda.bsm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

public interface TalentService {

	List<Category> 	findCategorys();
	String 			addTalent(Talent talent);
	ModelAndView 	talentImageUpload(MultipartHttpServletRequest request);
	Talent 			findTalentByTalentNo(Long talentNo);
	String 			modifyTalent(Talent talent);
	List<Talent> 	findBestSellerTalents();
	List<Talent> 	findTopRatedTalents();
	List<Talent> 	findNewTrendTalents();
	List<Talent> 	findRandomTalents();
	
}
