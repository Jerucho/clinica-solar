package com.sharp.clinica_solar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sharp.clinica_solar.interceptors.SesionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SesionInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/css/**",
				"/js/**", "/images/**", "/error", "/resources/**");
	}
}