package com.kakao.jPanda.bsm.configration;


import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class FileUploadConfig {
	
	// 파일 업로드 사이즈 설정
	@Bean
    public CommonsMultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10485760); // 10MB
        resolver.setMaxInMemorySize(1048576); // 1MB
        resolver.setUploadTempDir(new FileSystemResource(System.getProperty("java.io.tmpdir")));
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);
        return resolver;
    }
	// 에디터5 이미지 업로드
   	@Bean
   	MappingJackson2JsonView jsonView() {
   		return new MappingJackson2JsonView();
   	}
}
