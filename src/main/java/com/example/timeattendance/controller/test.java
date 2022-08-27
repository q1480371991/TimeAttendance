package com.example.timeattendance.controller;

import com.example.timeattendance.mapper.UserMapper;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class test {
    @Autowired
    UserService userService;
    UserMapper userMapper;

    @RequestMapping("/test1")
    public void test1(){


    }
}
