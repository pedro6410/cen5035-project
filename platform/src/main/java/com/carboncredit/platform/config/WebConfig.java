package com.carboncredit.platform.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginChecker())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/error", "/static/image/css/**", "/image/**");
    }


}
