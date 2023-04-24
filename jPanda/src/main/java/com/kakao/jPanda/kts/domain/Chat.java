package com.kakao.jPanda.kts.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Chat {
	private Long id;
	private String senderId;
	private String recieverId;
	private String message;
	private Timestamp chatDate;
}
