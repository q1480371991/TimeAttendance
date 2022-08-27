package com.example.timeattendance.pojo;

import lombok.Data;

@Data
public class Message {
    //0为群聊-自己的消息  1为群聊-别人的消息  2为私聊-自己的消息  3为私聊-别人的消息
    private String avatar;
    private Integer type;
    private String name;
    private String id;
    private String studentid;
    private String from;
    private String to;
    private String message;
    private String time;
}
