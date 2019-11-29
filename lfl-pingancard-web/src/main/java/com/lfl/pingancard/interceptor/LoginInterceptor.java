package com.lfl.pingancard.interceptor;

import com.lfl.utils.CookieUtils;
import com.lfl.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author: lifalong
 * @create: 2019-10-31 09:25
 * @description: 登录拦截器
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //   return true;
        // 在请求处理之前进行调用（Controller方法调用之前）,返回true才会继续往下执行，返回false取消当前请求
        // 获得cookie的token
        String token = CookieUtils.getCookieValue(request, "TOKEN");

        if (StringUtils.isNotBlank(token)) {
            try {

                Claims claims = TokenUtil.parseJWT(token);
                System.out.printf("token=%s存在，允许放行\r\n", token);
                Date date = new Date();
                request.setAttribute("username", claims.getId());
                request.setAttribute("id", claims.get("id"));
                Date expireTime = new Date((Long)claims.get("expireTime")) ;

                if (request.getRequestURI().equals("/user/query") ) {
                    return true;
                }

                if (expireTime.before(date)) {
                    // 表示过期的时间小于现在时间
                    response.setStatus(403); // 过期的，禁止访问
                    return false;
                }
                return true;
            } catch (Exception e) {
                response.setStatus(401);
                log.info("token={}不存在或过期,请重新登录", token);
                return false;
            }
        }
        response.setStatus(401);
        log.info("token={}不存在或过期,请重新登录", token);
        return false;
    }

}
