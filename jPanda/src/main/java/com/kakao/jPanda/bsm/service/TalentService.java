package com.kakao.jPanda.bsm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

public interface TalentService {

	List<Category> 	categoryList();
	void 			talentWrite(Talent talent);
	ModelAndView 	contentImageUpload(MultipartHttpServletRequest request);
	ModelAndView 	mainImageUpload(MultipartFile file, HttpServletRequest request);
	Talent 			getTalent(int talentNo);
	void 			talentUpdate(Talent talent);
	
}
