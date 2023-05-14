package com.kakao.jPanda.common.config;


import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class FileUploadConfig {
	
	// 파일 업로드 사이즈 설정
	@Bean
    public CommonsMultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10485760); // 파일 최대 크기 10MB
        resolver.setMaxInMemorySize(1048576); // 메모리 최대 크기 1MB
        resolver.setDefaultEncoding("UTF-8"); // 인코딩
        resolver.setResolveLazily(true); // 파일 업로드를 지연 처리
        return resolver;
    }
	// 에디터5 이미지 업로드를 위한 설정
   	@Bean
   	MappingJackson2JsonView jsonView() {
   		return new MappingJackson2JsonView();
   	}
   	
}
