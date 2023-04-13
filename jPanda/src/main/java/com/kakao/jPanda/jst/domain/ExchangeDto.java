package com.kakao.jPanda.jst.domain;

import lombok.Data;

@Data
public class ExchangeDto {
	private String exchangeNo;
	//sellerId
	private String id;
	//talentNo
	private String talentNo;
	//sellCount
	private Long sales;
	//sysdate
	private String submitDate;
	//null
	private String approveDate;
	//sellCount * saleBamboo
	private Long total;
	//(select grade from member where id = sellerId)
	private String grade;
	//(select ratio from member_grade where grade = (select grade from member where id = sellerId) )
	private Long ratio;
	//'검토중'
	private String status;
	//null
	private Long paymentAmount;
	
	
}
