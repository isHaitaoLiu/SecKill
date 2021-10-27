package cn.cugcs.sakura.seckill.config;

import cn.cugcs.sakura.seckill.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: seckill
 * @description: Web配置类
 * @author: Sakura
 * @create: 2021-10-26 10:26
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    /**
     * @Author sakura
     * @Description 拦截器配置
     * @Date 2021/10/26
     * @Param [registry]
     * @return void
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/bootstrap/**",
                        "/img/**",
                        "/jquery-validation/**",
                        "/js/**",
                        "/layer/**",
                        "goods_detail.html",
                        "goods_list.html",
                        "login.html",
                        "order_detail.html",
                        "register.html",
                        "/user/",
                        "/user",
                        "/user/login"
                        );
    }
}
