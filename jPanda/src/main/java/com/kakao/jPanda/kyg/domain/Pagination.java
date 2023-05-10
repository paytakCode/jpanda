package com.kakao.jPanda.kyg.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pagination {
	// 전체 글의 개수
	private int totalCount;
	// 전체 페이지의 개수
	private int totalPage;
	// 페이지당 글(row)의 수
	private int amount = 10;
	// 전체 블록의 개수, 페이지를 블록으로 묶음
	private int totalBlock;
	// 페이지당 보여지는 블록의 수
	private int pageBlock = 10;
	// 현재 페이지(블록) 번호
	private int currentPage = 1;
	// 페이지의 시작 행
	private int startNum;
	// 페이지의 마지막 행
	private int lastNum;
	// 마지막 블록 조사
	private int lastPageCheck;
	// 페이지의 시작 행
	private int startRow;
	// 페이지의 마지막 행
	private int endRow;
	// session에서 전달받은 Id
	private String chargerId;
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		startRow = (currentPage - 1) * amount + 1;
		endRow = startRow + amount - 1;
		totalPage = (int) Math.ceil((double)totalCount / amount);
		startNum = currentPage - (currentPage - 1) % pageBlock;
		lastNum = startNum + pageBlock - 1;
		
		if(lastNum > totalPage) {
			lastNum = totalPage;
		}
	}
}
