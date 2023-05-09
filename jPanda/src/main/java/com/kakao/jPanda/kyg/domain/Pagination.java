package com.kakao.jPanda.kyg.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pagination {
	private int totalCount;
	private int totalPage;
	private int amount = 10;
	private int totalBlock;
	private int pageBlock = 10;
	private int currentPage = 1;
	private int startNum;
	private int lastNum;
	private int lastPageCheck;
	private int startRow;
	private int endRow;
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
