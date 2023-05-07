package com.kakao.jPanda.kyg.service;

import lombok.Data;

/*
 * Paging
 * 단독서비스 - controller, DAO 연결 안함
 * service에서 모든것을 처리
 */
@Data
public class Paging {
	// 현재 페이지(블록) 번호
	private int currentPage = 1;
	// 페이지당 글(row)의 수
	private int rowPage = 10;
	// 페이지당 보여지는 블록의 수
	private int pageBlock = 10;
	// 페이지의 시작 행
	private int startRow;
	// 페이지의 마지막 행
	private int endRow;
	// 페이지의 시작 번호
	private int startPage;
	// 페이지의 마지막 번호
	private int endPage;
	// 마지막 블록 조사
	private int endBlockCheck;
	// 전체 글의 개수
	private int totalRow;
	// 전체 페이지의 개수
	private int totalPage;
	// 전체 블록의 개수, 페이지를 블록으로 묶음
	private int totalBlock;
	
	
	public Paging(int totalRow, String strCurrentPage) {
		this.totalRow = totalRow;
		
		// 사용자가 페이지를 지정하지 않았을 경우 기본적으로 1페이지를 보여줌
		if(strCurrentPage != null) {
			this.currentPage = Integer.parseInt(strCurrentPage);
		}
		
		startRow  = (currentPage - 1) * rowPage + 1;
		endRow	  = startRow + rowPage - 1;
		totalPage = (int)Math.ceil((double)totalRow / rowPage);
		startPage = currentPage - (currentPage - 1) % pageBlock;
		endPage	  = startPage + pageBlock - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
	}
	
}
