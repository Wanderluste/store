package com.cy.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 正确重写 WebMvcConfigurer 接口的方法
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置资源映射（替换为你的实际路径）
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:/Intelli2024/upload/");
    }
}
