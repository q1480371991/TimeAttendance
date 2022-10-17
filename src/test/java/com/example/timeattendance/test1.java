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
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;


import javax.sql.DataSource;
import java.io.FileNotFoundException;
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
    public  void test1() throws FileNotFoundException {
        String path="/C:/Users/123/Desktop/备份/time-attendence/TimeAttendance/target/test-classes/";
        autoJob.AutoRecordToExcel1(path);
//        /C:/Users/123/Desktop/%e5%a4%87%e4%bb%bd/time-attendence/TimeAttendance/target/test-classes/

//                //获取classes目录绝对路径  方式一
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//        //获取classes目录绝对路径  方式二
//        System.out.println(path);
//        String path1 = ResourceUtils.getURL("classpath:").getPath();
////输出目录：/F:/fileupload/fileupload/target/classes/
//
//        System.out.println(path1);


    }

}
