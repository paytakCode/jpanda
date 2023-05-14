package com.kakao.jPanda.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e){
		log.warn("error : {}, trace{} : ", e, e.getStackTrace());
		return ErrorResponseEntity.toResponseEntity(e.getErrCode());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseEntity> handleException(Exception e) {
		log.warn("error : {}, trace : {}", e, e.getStackTrace());
		return ErrorResponseEntity.toResponseEntity(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR).getErrCode());
    }
}
