package com.kakao.jPanda.chat.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ChatPartnerDto {
	
	@NotEmpty
	private String memberId;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private int unreadCount;
}
