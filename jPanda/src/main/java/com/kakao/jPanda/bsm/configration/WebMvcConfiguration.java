package com.kakao.jPanda.bsm.configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		int index = System.getProperty("user.dir").indexOf("\\jPanda");
		String path = System.getProperty("user.dir").substring(0, index) + "/uploadImage/";
		
		registry.addResourceHandler("/uploadImage/**").addResourceLocations("file:///" + path);
	}
}
