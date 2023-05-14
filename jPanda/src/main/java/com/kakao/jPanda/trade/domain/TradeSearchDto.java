package com.kakao.jPanda.trade.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeSearchDto {
	private Timestamp standardTime;
	private String userId;
}
