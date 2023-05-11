package com.kakao.jPanda.common.config;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kakao.jPanda.common.interceptor.AdminInterceptor;
import com.kakao.jPanda.common.interceptor.CommonInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("");
		
		registry.addInterceptor(adminInterceptor)
				.addPathPatterns("/admin/**");
	}
	
	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }
    
	// 이미지 업로드 시 저장 경로 설정(외부 디텍토리)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		int index = System.getProperty("user.dir").indexOf(File.separator + "jPanda");
		String path = System.getProperty("user.dir").substring(0, index) + File.separator + "uploadImage" + File.separator;
		registry.addResourceHandler("/uploadImage/**").addResourceLocations("file:" + path);
	}
	
}
