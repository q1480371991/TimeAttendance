package com.example.timeattendance.ws;

import com.example.timeattendance.pojo.Message;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.UserService;
import com.example.timeattendance.utils.MessageUtils;
import com.example.timeattendance.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value="/chart",configurator = GetHttpSessionConfigurator.class)
@Component
//每个客户端对应一个chatendpoint
public class ChatEndpoint {

    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        ChatEndpoint.userService=userService;
    }
    /**
     * 用来储存在线用户的容器
     */
    public static Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    public static Map<String,Message> joinNotice=new HashMap<String,Message>();

    /**
     * 申明session对象，通过该对象可以发送消息给指定的客户端
     * 用来给客户端发送消息
     */
    private Session session;

    /**
     * 声明一个httpsession对象，获取之前在httpsession对象中存储的用户名
     * 用来获取在登录成功后，放在httpsession域中存放的username
     */
    private HttpSession httpSession;

    public boolean check(String studentid){
        boolean flag=false;
        Set<String> studentids = joinNotice.keySet();
        for (String stid : studentids)
        {
            if(stid.equals(studentid))
            {
                flag=true;
                break;
            }
        }
        return flag;
    }

    public void toEveryone(String message){
        //所有登录用户名称
        Set<String> studentids = onlineUsers.keySet();
        for (String studentid : studentids){
//            System.out.println(studentid);
            ChatEndpoint chatEndpoint = onlineUsers.get(studentid);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();

            try {
                basicRemote.sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig)  {
        ObjectMapper objectMapper = new ObjectMapper();
        //将局部的session对象赋值给成员session
        this.session=session;
        //通过websocket配置对象获取httpsession对象
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession=httpSession;

        String  studentid = (String) httpSession.getAttribute("studentid");
        System.out.println(studentid+"websocket建立连接onOpen");





        //检查有没有重复连接websocket的
        if(!check(studentid)){
            //存放到onlineUser中保存
            onlineUsers.put(studentid,this);
            User user = (User) userService.SelectOneById(studentid).getData();
            Message message = new Message();
            message.setType(2);
            message.setName(user.getName());
            message.setId(user.getId().toString());
            message.setStudentid(studentid);
            message.setAvatar("one.jpg");//先写死

            joinNotice.put(user.getStudentId(),message);
        }



        //上线提醒
        Set<String> studentids = onlineUsers.keySet();
        for (String stid : studentids){

            ChatEndpoint chatEndpoint = onlineUsers.get(stid);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();


            try {
                String jsondata = MessageUtils.getMessage(true, joinNotice);
                basicRemote.sendText(jsondata);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @OnMessage
    public void onMessage(String msg,Session session) throws IOException {
        System.out.println(msg);
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> studentids = onlineUsers.keySet();
        Message recmsg = objectMapper.readValue(msg, Message.class);
        if(recmsg.getType()==1&&recmsg.getTo()==null){
            //群聊消息
            for (String studentid: studentids
                 ) {
                //
                if(!studentid.equals(recmsg.getFrom()))
                {
                    //发送群聊消息给在线的每一个人，除了发送者
                    ChatEndpoint chatEndpoint = onlineUsers.get(studentid);
                    //获取推送对象
                    RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
                    try {
                        String jsondata = MessageUtils.getMessage(false, recmsg);
                        basicRemote.sendText(jsondata);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(recmsg.getType()==3&&recmsg.getTo()!=null){
            //私聊消息
            //发送私聊消息给在线的to
            ChatEndpoint chatEndpoint = onlineUsers.get(recmsg.getTo());
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
            try {
                String jsondata = MessageUtils.getMessage(false, recmsg);
                basicRemote.sendText(jsondata);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }




    /**关闭时调用*/
    @OnClose
    public void onClose(Session session) {
        String studentid = (String) httpSession.getAttribute("studentid");
        //
        onlineUsers.remove(studentid);

        joinNotice.remove(studentid);
        System.out.println(studentid+"websocket断开连接onClose");
        System.out.println(onlineUsers.keySet());
        System.out.println(joinNotice.keySet());
        //下线提醒
        Set<String> studentids = onlineUsers.keySet();
        for (String stid : studentids){

            ChatEndpoint chatEndpoint = onlineUsers.get(stid);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();


            try {
                String jsondata = MessageUtils.getMessage(true, joinNotice);
                basicRemote.sendText(jsondata);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
