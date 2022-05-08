package com.ssafy.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.web.interceptor.SessionInterceptor;

@Configuration
public class Config implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
		.addPathPatterns("/auth/mypage")
		.addPathPatterns("/auth/update")
		.addPathPatterns("/auth/delete")
		.addPathPatterns("board/createPost")
		.addPathPatterns("/board/updatePost/{no}");
	}
}
