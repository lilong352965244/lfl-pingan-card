package com.lfl.pingancard.interceptor;

import com.lfl.constant.CommonConstant;
import com.lfl.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author: lifalong
 * @create: 2019-10-31 09:25
 * @description: 登录拦截器
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
//        // 在请求处理之前进行调用（Controller方法调用之前）,返回true才会继续往下执行，返回false取消当前请求
//        String tokenCode = request.getHeader("T_TOKEN");
//        String key = CommonConstant.REDIS_KEY_PREX;
//        if (tokenCode != null && !"".equals(tokenCode)) {
//            if (stringRedisTemplate.hasKey("user:info:" + tokenCode)) {
//                System.out.printf("token=%s存在，允许放行\r\n", tokenCode);
//                // 重新设置redis,cookie 有效时间
//                this.stringRedisTemplate.expire(key, CommonConstant.REDIS_EXPIRE_SECONDS, TimeUnit.SECONDS);
//                CookieUtils.setCookie(request, response, "T_TOKEN", tokenCode, CommonConstant.REDIS_EXPIRE_SECONDS);
//                return true;
//            }
//        }
//        response.setStatus(415);
//        log.info("token={}不存在或过期,请重新登录", tokenCode);
//        return false;
    }

}
