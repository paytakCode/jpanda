package com.kakao.jPanda.bsm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakao.jPanda.bsm.domain.Category;
import com.kakao.jPanda.bsm.domain.Talent;

import lombok.RequiredArgsConstructor;

@Repository
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
	public void talentUpload(Talent talent) {
		try {
			session.insert("talentUpload", talent);
		} catch (Exception e) {
			System.out.println("TalentDaoImpl talentUpload e.getMessage() ->" + e.getMessage());
		}
	}
	
}
