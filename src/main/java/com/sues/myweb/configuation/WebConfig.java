package com.sues.myweb.configuation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Alan
 * @date 2022/12/28 17:43
 * 防止跨域
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .maxAge(3600);
    }
}