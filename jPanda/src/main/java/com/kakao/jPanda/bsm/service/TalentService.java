package com.kakao.jPanda.bsm.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.domain.CategoryDto;
import com.kakao.jPanda.bsm.domain.TalentDto;

public interface TalentService {

	List<CategoryDto> 	findCategorys();
	String 			addTalent(TalentDto talent, HttpSession session);
	ModelAndView 	talentImageUpload(MultipartHttpServletRequest request);
	TalentDto 		findTalentByTalentNo(Long talentNo);
	String 			modifyTalent(TalentDto talent, HttpSession session);
	Model			findMainPageTalents(Model model);
	
}
