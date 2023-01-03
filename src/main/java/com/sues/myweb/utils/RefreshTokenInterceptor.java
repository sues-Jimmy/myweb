package com.sues.myweb.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Alan
 * @date 2022/12/30 16:03
 **/
public class RefreshTokenInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        System.out.println("打印cookies"+cookies);
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    String account = stringRedisTemplate.opsForValue().get(token);
                    UserHolder.saveAccount(account);
                    Cookie newCookie = new Cookie("token", account);
                    newCookie.setMaxAge(180);
                    response.addCookie(newCookie);
                    stringRedisTemplate.expire(token, 3, TimeUnit.MINUTES);
                    System.out.println("刷新有效期成功");
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeAccount();
    }
}
