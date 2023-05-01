package com.kakao.jPanda.bsm.configration;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		int index = System.getProperty("user.dir").indexOf(File.separator + "jPanda");
		String path = System.getProperty("user.dir").substring(0, index) + File.separator + "uploadImage" + File.separator;
		registry.addResourceHandler("/uploadImage/**").addResourceLocations("file:" + path);
	}
}
