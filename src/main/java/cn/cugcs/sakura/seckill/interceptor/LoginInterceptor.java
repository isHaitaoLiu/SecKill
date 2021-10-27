package cn.cugcs.sakura.seckill.interceptor;

import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: seckill
 * @description: 检查用户是否登录，也即cookie是否存在
 * @author: Sakura
 * @create: 2021-10-26 10:13
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtil.getCookieValue(request, "userTicket");
        if (cookieValue == null) return false;
        User user = (User) redisTemplate.opsForValue().get("user:" + cookieValue);
        return user != null;
    }
}
