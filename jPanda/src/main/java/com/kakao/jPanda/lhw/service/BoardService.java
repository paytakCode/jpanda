package com.kakao.jPanda.lhw.service;

import java.util.List;

import com.kakao.jPanda.lhw.domain.Notice;
import com.kakao.jPanda.lhw.domain.Talent;

public interface BoardService {

	List<Talent> getTalentList();

	List<Notice> getNoticeList();

}
