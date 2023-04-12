package com.kakao.jPanda.lhw.dao;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

public interface TalentDao {

	List<Talent> getTalentList();

	List<Notice> getNoticeList();

}
