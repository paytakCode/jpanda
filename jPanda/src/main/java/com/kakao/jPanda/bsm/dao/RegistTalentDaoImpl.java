package com.kakao.jPanda.bsm.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class RegistTalentDaoImpl implements TalentDao{
	private final SqlSession session;
	@Override
	public List<Category> selectCategorys() {
		List<Category> categoryList = null;
		try {
			categoryList = session.selectList("selectCategorys");
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectCategorys e.getMessage() ->" + e.getMessage());
		}
		return categoryList;
	}
	@Override
	public int insertTalent(Talent talent) {
		int result = 0;
		try {
			result = session.insert("insertTalent", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl insertTalent e.getMessage() ->" + e.getMessage());
		}
		return result;
	}
	@Override
	public Talent selectTalentBytalentNo(Long talentNo) {
		Talent talent = null;
		try {
			talent = session.selectOne("RegistselectTalentBytalentNo", talentNo);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectTalentBytalentNo e.getMessage() ->" + e.getMessage());
		}
		return talent;
	}
	@Override
	public int updateTalent(Talent talent) {
		int result = 0;
		try {
			result = session.update("updateTalent", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl updateTalent e.getMessage() ->" + e.getMessage());
		}
		return result;
	}
	@Override
	public List<Talent> selectBestSellerTalents() {
		List<Talent> selectBestSellerTalents = null;
		try {
			selectBestSellerTalents = session.selectList("selectBestSellerTalents");
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectBestSellerTalents e.getMessage() ->" + e.getMessage());
		}
		return selectBestSellerTalents;
	}
	@Override
	public List<Talent> selectTopRatedTalents() {
		List<Talent> selectTopRatedTalents = null;
		try {
			selectTopRatedTalents = session.selectList("selectTopRatedTalents");
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectTopRatedTalentTalents e.getMessage() ->" + e.getMessage());
		}
		return selectTopRatedTalents;
	}
	@Override
	public List<Talent> selectNewTrendTalents() {
		List<Talent> selectNewTrendTalents = null;
		try {
			selectNewTrendTalents = session.selectList("selectNewTrendTalents");
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectNewTrendTalents e.getMessage() ->" + e.getMessage());
		}
		return selectNewTrendTalents;
	}
	@Override
	public List<Talent> selectRandomTalents() {
		List<Talent> selectRandomTalents = null;
		try {
			selectRandomTalents = session.selectList("selectRandomTalents");
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectRandomTalents e.getMessage() ->" + e.getMessage());
		}
		return selectRandomTalents;
	}
	
}
