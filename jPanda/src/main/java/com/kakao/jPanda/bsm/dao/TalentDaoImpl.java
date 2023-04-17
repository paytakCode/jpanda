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
			System.out.println("TalentDaoImpl categoryList e.getMessage() ->" + e.getMessage());
		}
		return categoryList;
	}
	@Override
	public void talentAdd(Talent talent) {
		try {
			session.insert("talentAdd", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl talentWrite e.getMessage() ->" + e.getMessage());
		}
	}
	@Override
	public Talent getTalent(Long talentNo) {
		Talent talent = null;
		try {
			talent = session.selectOne("getTalent", talentNo);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl getTalent e.getMessage() ->" + e.getMessage());
		}
		return talent;
	}
	@Override
	public void talentModify(Talent talent) {
		try {
			session.update("talentModify", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl talentUpdate e.getMessage() ->" + e.getMessage());
		}
		
	}
	
}
