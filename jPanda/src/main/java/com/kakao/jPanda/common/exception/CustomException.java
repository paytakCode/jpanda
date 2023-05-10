package com.kakao.jPanda.common.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	ErrorCode errCode;
	
	public CustomException(ErrorCode errCode) {
		this.errCode = errCode;
	}
}
