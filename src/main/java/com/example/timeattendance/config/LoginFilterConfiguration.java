package com.example.timeattendance.config;

import com.example.timeattendance.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
//解决springboot-vue前后端分离项目跨域session不一致的问题
@Configuration
public class LoginFilterConfiguration {
    @Autowired LoginFilter loginFilter;
    @Bean
    public RegistrationBean myFilter(){
//        LoginFilter loginFilter = new LoginFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(loginFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
