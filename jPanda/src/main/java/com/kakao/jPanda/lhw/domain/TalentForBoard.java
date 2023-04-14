package com.kakao.jPanda.lhw.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TalentForBoard {
	// 재능 판매 게시판 리스트 불러올때 필요한 데이터들
	private Long talentNo;
	private String sellerId;
	private String mainImg;
	private String title;
	private String summary;
	private Long bamboo;
	private Long saleBamboo;
	
	// 리뷰 별점 
	private Long bambooScore;
	
	
	
}
