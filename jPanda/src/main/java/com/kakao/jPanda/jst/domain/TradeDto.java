package com.kakao.jPanda.jst.domain;

import java.sql.Timestamp;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TradeDto extends TalentDto{
	//member
	private String memberId;
	private String password;
	private String name;
	private String tel;
	private String email;
	private String birth;
	private String gender;
	private String memberStatus;
	private String memberGrade;
	
	//talent_refund
	private String refundPurchaseNo; //purchaseNo
	private String refundStatus;
	private String issue;
	private Timestamp refundSubmitDate; //submitDate
	private Timestamp refundApproveDate; //approveDate
	private Long refundBamboo;
	
	//member_grade
	private String grade;
	private Long gradeRatio; //exchangeRatio
	
	//exchange
	private String exchangeNo;
	private String exchangeId; //sellerId
	private String exchangeTalentNo; //talentNo
	private Long sales;
	private Timestamp exchangeSubmitDate;
	private Timestamp exchangeApproveDate;
	private Long total;
	private String exchangeGrade; //grade
	private Long exchangeRatio; //ratio
	private String exchangeStatus; //status
	private Long paymentAmount;
	
	//bamboo_use
	private String bambooUsePurchaseNo; //purchaseNo
	private String buyerId;
	private String bambooUsetalentNo; //talentNo
	private Timestamp purchaseDate;
	private String useBamboo;
	
	//ExtraValues
		//TradeMapper.xml selectTradeListByParaMap
	private String listType;
		//TradeMapper.xml selectTradeListByParaMap
	private Long sellCount;
		//TradeMapper.xml selectTradeListByParaMap
	private String refundableDate;
	
}
