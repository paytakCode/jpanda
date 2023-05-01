package com.kakao.jPanda.jst.exception;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final SqlSession sqlsession;
	
	@Autowired
	public GlobalExceptionHandler(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	@ResponseBody	
	@ExceptionHandler(SQLException.class)
	public String sqlException(SQLException e) {
		log.error("SQLException Occured");
		SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(sqlsession.getConfiguration()
																					.getEnvironment()
																					.getDataSource());
		
		return translator.translate("SQLException", null , e).getMessage();
	}
	
}//end class
