package com.kakao.jPanda.jst.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeSearchDto {
	private Timestamp standardTime;
	private String userId;
}
