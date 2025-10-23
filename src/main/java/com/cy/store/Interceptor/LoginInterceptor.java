package com.cy.store.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**定义一个拦截器, SpringMVC*/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局是否有uid数据，如果有则放行，没有则重定向至登陆页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return true放行 否则拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        if(obj == null){
            //重定向
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
