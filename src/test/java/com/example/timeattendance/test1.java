package com.example.timeattendance;



import com.example.timeattendance.mapper.UserMapper;
import com.example.timeattendance.pojo.Message;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.AutoJob;
import com.example.timeattendance.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


@SpringBootTest
public class test1 {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AutoJob autoJob;
    @Autowired
    DataSource dataSource;


    @Test
    public  void test1() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("····················标题test···················");
        simpleMailMessage.setText("····················内容test·············");
        simpleMailMessage.setTo("2531474710@qq.com");
//        simpleMailMessage.setFrom(username);
//        mailSender.send(simpleMailMessage);
    }

}
