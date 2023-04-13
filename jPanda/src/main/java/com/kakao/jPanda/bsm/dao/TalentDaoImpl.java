package com.kakao.jPanda.bsm.dao;

import static org.hamcrest.CoreMatchers.nullValue;

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
	public List<Category> categoryList() {
		List<Category> categoryList = null;
		try {
			categoryList = session.selectList("CategoryList");
		} catch (Exception e) {
			System.out.println("TalentDaoImpl categoryList e.getMessage() ->" + e.getMessage());
		}
		return categoryList;
	}
	@Override
	public void talentWrite(Talent talent) {
		try {
			session.insert("talentWrite", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl talentWrite e.getMessage() ->" + e.getMessage());
		}
	}
	@Override
	public Talent getTalent(int talentNo) {
		Talent talent = null;
		try {
			talent = session.selectOne("getTalent", talentNo);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl getTalent e.getMessage() ->" + e.getMessage());
		}
		return talent;
	}
	@Override
	public void talentUpdate(Talent talent) {
		try {
			session.update("updateTalent", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl talentUpdate e.getMessage() ->" + e.getMessage());
		}
		
	}
	
}
