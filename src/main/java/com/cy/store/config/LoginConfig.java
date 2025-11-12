package com.cy.store.config;

import com.cy.store.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**处理器拦截功能*/
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器并添加拦截规则
        registry.addInterceptor(new LoginInterceptor())
                //拦截所有请求
                .addPathPatterns("/**")
                //也可以用一个List<String> 来设置排除拦截的资源
                //放行静态资源
                .excludePathPatterns("/web/login.html","/web/index.html",
                        "/web/register.html","/web/product.html",
                        "/web/components/**","/web/search.html",
                        "/web/paySuccess.html","/web/components/**",
                        "/css/**","/bootstrap3/**", "/images/**","/js/**")
                //放行请求接口和支付宝沙箱接口
                .excludePathPatterns("/users/**","/addresses/**","/file/**","/districts/**",
                        "/products/**","/carts/**","/orders/**","/kaptcha/**",
                        "/alipay/**")
                //不放行/error页面有可能导致白名单失效假象
                .excludePathPatterns("/error");
    }
}