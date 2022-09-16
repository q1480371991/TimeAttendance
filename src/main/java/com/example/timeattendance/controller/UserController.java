package com.example.timeattendance.controller;

import com.example.timeattendance.pojo.Reporter;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.UserService;
import com.example.timeattendance.utils.R;
import com.example.timeattendance.utils.TimeUtils;
import com.example.timeattendance.utils.code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@CrossOrigin()
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/addavatar")
    public R AddAvatar(@RequestBody User user){
        return userService.AddAvatar(user);
    }

    @RequestMapping("/report")
    public R ReportOthers(@RequestBody Reporter reporter)
    {
        System.out.println(reporter);
        R r = userService.Report(reporter.getReportid(), reporter.getReporterid());
        return r;
    }

    @RequestMapping("/selectone")
    public R SelectOneById(@PathParam("studentid") String studentid)
    {
        return userService.SelectOneById(studentid);
    }

    @RequestMapping("/adduser")
    public R Add(@RequestBody User user)
    {
        R r = new R(userService.AddUser(user));
        r.setMsg("添加成功");
        r.setCode(code.SAVE_OK);
        return r;
    }

    @RequestMapping("/login")
    public R Login(@PathParam("studentid") String studentid,
                   HttpSession httpSession)
    {
        httpSession.setAttribute("studentid",studentid);
        return userService.LoginByid(studentid);
    }

    @RequestMapping("/logout")
    public R Logout(@PathParam("studentid") String studentid,
                    HttpSession session)
    {
        R r =userService.LogoutByid(studentid);
        User user=(User) r.getData();
        if(r.getMsg().equals(studentid+"签退成功")){
            boolean a = userService.AddTime(user);
            boolean b = userService.RecordTime(user, 1);
            r.setRecordtime(TimeUtils.TimeCalculate(user.getStartTime(),user.getEndTime()));
        }


        return r;
    }
}
