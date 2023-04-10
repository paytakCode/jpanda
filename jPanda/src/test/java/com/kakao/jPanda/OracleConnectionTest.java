package com.kakao.jPanda;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OracleConnectionTest {
	    private static final String DRIVER
	    ="oracle.jdbc.driver.OracleDriver";
	    //연결문자열 jdbc:oracle:thin:@호스트:포트:sid
	    private static final String URL
	    ="jdbc:oracle:thin:@localhost:1521:xe";
	    private static final String USER="jpanda";//아이디
	    private static final String PW="jpanda";//
	    
	    @Test//Junit이 테스트하는 메소드
	    public void test() throws ClassNotFoundException {
	        Class.forName(DRIVER);//jdbc 드라이버 로딩
	        
	        //괄호안에 리소스 자동 삭제 
	        try(Connection conn=DriverManager.getConnection(URL, USER, PW)){
	            log.info("오라클에 연결되었습니다");
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
}
