package com.kakao.jPanda.kts.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
	
	@NotEmpty
	private String senderId;

	@NotEmpty
	private String receiverId;

	@NotEmpty
	private String message;
	
	private Timestamp chatDate;
}
