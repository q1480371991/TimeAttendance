package com.example.timeattendance.config;

import com.example.timeattendance.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {
    //打卡时间过滤器
    @Autowired
    TimeInterceptor timeInterceptor;

    String[] PathPatterns = {
            "/login",
            "/logout"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns(PathPatterns);
    }
}
