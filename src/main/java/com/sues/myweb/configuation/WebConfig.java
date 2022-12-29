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
    //用于配置跨域请求的映射规则
    //这里是因为同源策略----浏览器只允许同源网站之间的脚本交互(即协议、端口、域名相同)，而不允许跨域网站之间的脚本交互从而避免恶意网站利用跨域
    //脚本攻击用户的隐私信息----也可以使用nginx来避免
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //表示对所有路径都进行跨域请求配置
        registry.addMapping("/**")
                //表示允许的请求方法有两种get和post
                .allowedMethods("GET","POST")
                //表示请求头为任意
                .allowedHeaders("*")
                .allowedOrigins("*")
                //表示预检请求的有效事件为3600s
                .maxAge(3600);
    }
}