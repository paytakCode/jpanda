package com.kakao.jPanda.bsm.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.bsm.domain.CategoryDto;
import com.kakao.jPanda.bsm.domain.TalentDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class RegistTalentDaoImpl implements TalentDao{
	private final SqlSession session;
	@Override
	public List<CategoryDto> selectCategorys() {
		List<CategoryDto> categoryList = null;
		try {
			categoryList = session.selectList("selectCategorys");
		} catch (Exception e) {
			log.error("TalentDaoImpl selectCategorys e.getMessage() ->" + e.getMessage());
		}
		return categoryList;
	}
	@Override
	public int insertTalent(TalentDto talent) {
		int result = 0;
		try {
			result = session.insert("insertTalent", talent);
		} catch (Exception e) {
			log.error("TalentDaoImpl insertTalent e.getMessage() ->" + e.getMessage());
		}
		return result;
	}
	@Override
	public TalentDto selectTalentBytalentNo(Long talentNo) {
		TalentDto talent = null;
		try {
			talent = session.selectOne("selectRegistTalentBytalentNo", talentNo);
		} catch (Exception e) {
			log.error("TalentDaoImpl selectTalentBytalentNo e.getMessage() ->" + e.getMessage());
		}
		return talent;
	}
	@Override
	public int updateTalent(TalentDto talent) {
		int result = 0;
		try {
			result = session.update("updateRegistTalent", talent);
		} catch (Exception e) {
			log.error("TalentDaoImpl updateTalent e.getMessage() ->" + e.getMessage());
		}
		return result;
	}
	@Override
	public List<TalentDto> selectBestSellerTalents() {
		List<TalentDto> selectBestSellerTalents = null;
		try {
			selectBestSellerTalents = session.selectList("selectBestSellerTalents");
		} catch (Exception e) {
			log.error("TalentDaoImpl selectBestSellerTalents e.getMessage() ->" + e.getMessage());
		}
		return selectBestSellerTalents;
	}
	@Override
	public List<TalentDto> selectTopRatedTalents() {
		List<TalentDto> selectTopRatedTalents = null;
		try {
			selectTopRatedTalents = session.selectList("selectTopRatedTalents");
		} catch (Exception e) {
			log.error("TalentDaoImpl selectTopRatedTalentTalents e.getMessage() ->" + e.getMessage());
		}
		return selectTopRatedTalents;
	}
	@Override
	public List<TalentDto> selectNewTrendTalents() {
		List<TalentDto> selectNewTrendTalents = null;
		try {
			selectNewTrendTalents = session.selectList("selectNewTrendTalents");
		} catch (Exception e) {
			log.error("TalentDaoImpl selectNewTrendTalents e.getMessage() ->" + e.getMessage());
		}
		return selectNewTrendTalents;
	}
	@Override
	public List<TalentDto> selectRandomTalents() {
		List<TalentDto> selectRandomTalents = null;
		try {
			selectRandomTalents = session.selectList("selectRandomTalents");
		} catch (Exception e) {
			log.error("TalentDaoImpl selectRandomTalents e.getMessage() ->" + e.getMessage());
		}
		return selectRandomTalents;
	}
	
}
