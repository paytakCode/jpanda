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
public class TalentDaoImpl implements TalentDao{
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
	public void insertTalent(Talent talent) {
		try {
			session.insert("insertTalent", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl insertTalent e.getMessage() ->" + e.getMessage());
		}
	}
	@Override
	public Talent selectTalentBytalentNo(Long talentNo) {
		Talent talent = null;
		try {
			talent = session.selectOne("selectTalentBytalentNo", talentNo);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl selectTalentBytalentNo e.getMessage() ->" + e.getMessage());
		}
		return talent;
	}
	@Override
	public void updateTalent(Talent talent) {
		try {
			session.update("updateTalent", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl updateTalent e.getMessage() ->" + e.getMessage());
		}
		
	}
	
}
