package com.example.timeattendance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class MyEmail {

    @Value("${spring.mail.username}")
    private static String myemail;

    @Autowired
    private static JavaMailSender mailSender;
    public static void SendEmail(String to,String title,String msg){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(msg);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom( myemail);
        mailSender.send(simpleMailMessage);
    }

}
