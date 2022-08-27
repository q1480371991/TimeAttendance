package com.example.timeattendance;



import com.example.timeattendance.pojo.Message;
import com.example.timeattendance.pojo.tset11;
import com.example.timeattendance.service.AutoJob;
import com.example.timeattendance.service.UserService;
import com.example.timeattendance.utils.MessageUtils;
import com.example.timeattendance.ws.ChatEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@SpringBootTest
public class test1 {
    @Autowired
    UserService userService;

    @Autowired
    AutoJob autoJob;

    @Test
    public  void test1() throws JsonProcessingException {
//        Map<String, Integer> onlineUsers = new HashMap<>();
//        onlineUsers.put("test1",1);
//        onlineUsers.put("test2",2);
//        System.out.println(onlineUsers);
//        onlineUsers.remove("test1");
//        System.out.println(onlineUsers);
        Collection<Message> joinNotice=new ArrayList<Message>();
        Message message1 = new Message();
        message1.setStudentid("17");
        Message message2 = new Message();
        message2.setStudentid("18");
        joinNotice.add(message1);
        joinNotice.add(message2);
        System.out.println(joinNotice);

        joinNotice.remove(message1);
        System.out.println(joinNotice);
    }

}
