package com.example.timeattendance.controller;

import com.example.timeattendance.pojo.TimeRecord;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.UserService;
import java.util.Collection;

import com.example.timeattendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
public class ShowController {
    @Autowired
    UserService userService;

    @RequestMapping("/showonline")
    public R ShowOnline()
    {
        R r=new R();
        Collection<User> result=userService.GetOnline();
        if(result!=null)
        {
            r.setFlag(true);
            r.setData(result);
        }else{
            r.setFlag(false);
            r.setData("查询失败");
        }
        return r;
    }

    @RequestMapping("/mytimerecored")
    public R MyTimeRecord(@PathParam("userid") String userid)
    {
        R r=new R();
        Collection<TimeRecord> result=userService.GetMyTimeRecord(userid);
        if(result!=null)
        {
            r.setFlag(true);
            r.setData(result);
        }else{
            r.setFlag(false);
            r.setData("查询失败");
        }
        return r;
    }

    @RequestMapping("/topthree")
    public R ShowTopthree(@PathParam("grade") String grade)
    {
        R r=new R();
        Collection<User> result = userService.GetTopthree(grade);
        if(result!=null)
        {
            r.setFlag(true);
            r.setData(result);
        }else{
            r.setFlag(false);
            r.setData("查询失败");
        }
        return r;


    }

    @RequestMapping("/showbygrade")
    public R ShowOnlineByGrade(@PathParam("grade") String grade,
                               @PathParam("isonline") String isonline)
    {
        R r=new R();
        Collection<User> result = userService.SelectByGrade(grade,isonline);
        if(result!=null)
        {
            r.setFlag(true);
            r.setData(result);
        }else{
            r.setFlag(false);
            r.setData("查询失败");
        }
        return r;

    }

}
