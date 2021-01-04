package com.seg02.oursudoku.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ResourcesConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/jportal-jsccbsite/**").addResourceLocations("classpath:/jportal-jsccbsite/");
		registry.addResourceHandler("/seat/**").addResourceLocations("classpath:/static/seat/");
		registry.addResourceHandler("/stranded/**").addResourceLocations("classpath:/static/stranded/");
	}
}