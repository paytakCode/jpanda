package com.kakao.jPanda.bsm.configration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class FileUploadConfig {
	
	    // 에디터5
    	@Bean
    	MappingJackson2JsonView jsonView() {
    		return new MappingJackson2JsonView();
    	}
	    
	    
	 
}
