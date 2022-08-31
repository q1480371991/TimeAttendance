package com.example.timeattendance.controller;

import com.example.timeattendance.mapper.UserMapper;
import com.example.timeattendance.pojo.Reporter;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.UserService;
import com.example.timeattendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class test {
    @Autowired
    UserService userService;
    UserMapper userMapper;

    @RequestMapping("/test1")
    public R test1(@RequestBody Reporter reporter)
    {
        System.out.println(reporter);
        System.out.println(reporter.getClass());
        return null;
    }
    @RequestMapping("/test2")
    public R test2(@RequestParam("reportid") String reportid,
                   @RequestParam("reporterid") String reporertid
                   )
    {
        System.out.println(reportid);
        System.out.println(reporertid);
        return null;
    }
}
