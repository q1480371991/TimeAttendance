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
//        System.out.println(session);



//        User user = userService.LoginByid(studentid);
//        R r = new R();
//        if(user!=null)
//        {
//            r.setFlag(true);
//            r.setData(user);
//            r.setMsg("签到成功");
//        }else {
//            r.setFlag(false);
//            r.setData(user);
//            r.setMsg("签到失败");
//        }

        return userService.LoginByid(studentid);
    }

    @RequestMapping("/logout")
    public R Logout(@PathParam("studentid") String studentid,
                    HttpSession session)
    {
//        System.out.println(session);
//        System.out.println(session.getAttribute("username")+"testsession");

//        R r = new R();
//        User user = (User)userService.LogoutByid(studentid).getData();
//        r.setData(user);
//        if(user!=null)
//        {
//            r.setFlag(true);
//            userService.AddTime(user);
//            String duration = TimeUtils.TimeCalculate(user.getStartTime(), user.getEndTime());
//            r.setMsg("本次签到时长:"+duration+"h");
//        }
//        else r.setFlag(false);
//
//        return r;
        R r =userService.LogoutByid(studentid);
        User user=(User) r.getData();
        if(r.getMsg().equals(studentid+"签退成功")){
            boolean a = userService.AddTime(user);
            boolean b = userService.RecordTime(user, 1);
            r.setRecordtime(TimeUtils.TimeCalculate(user.getStartTime(),user.getEndTime()));
        }

//
//        String s = TimeUtils.TimeCalculate(user.getStartTime(), user.getEndTime());

        return r;
    }
}
