package com.example.timeattendance.utils;

import com.example.timeattendance.pojo.User;
import lombok.Data;

import java.util.Collection;

@Data
//数据工具类
public class R {
    private  Boolean flag;//请求状态
    private  String msg;
    private  String recordtime;
    private Object data;
    private  Integer code;
    public R(Boolean flag)
    {
        this.flag=flag;
    }
    public R() {}


}
