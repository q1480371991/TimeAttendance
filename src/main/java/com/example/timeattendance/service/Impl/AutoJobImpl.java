package com.example.timeattendance.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.example.timeattendance.mapper.UserMapper;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.AutoJob;
import com.example.timeattendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

@Service
public class AutoJobImpl implements AutoJob {
    String PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath();
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Override
    //每天23:30都执行自动签退
    @Scheduled(cron = "00 30 23 * * *")
    public boolean AutoLogout() {
        System.out.println("23:30自动签退开始");
        boolean flag=true;
        boolean a = userService.LogoutAll(0);
        if(a){
            flag=true;
        }else{
            flag=false;
        }
        System.out.println("23:30自动签退结束");
        return flag;
    }

    @Override
    //每周日23.50都执行打卡时间清零
    @Scheduled(cron = "00 50 23 * * 1")
    public boolean AutoReset() {
        System.out.println("自动Reset开始");
        boolean flag=true;
        Collection<User> users = userService.GetAll();
        for (User user:users
             ) {
            user.setWeekTime(0);
            int i = userMapper.updateById(user);
            if(i==1)flag=true;
            else flag=false;
        }
        if(flag) System.out.println("自动Reset成功");
        else System.out.println("自动Reset失败");
        System.out.println("自动Reset结束");
        return flag;
    }
    //每周日23.40都将本周打卡记录导出到Excel表格
    @Scheduled(cron = "00 40 23 * * 1")
    public void AutoRecordToExcel(){
        boolean flag=true;
        System.out.println("星期天23:40自动导出Excel开始");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());
        //导出数据到Excel表格
        String filename=PATH+date+".xlsx";
        Collection<User> users = userService.GetAll();
        EasyExcel.write(filename,User.class).sheet("模板").doWrite(users);
        System.out.println("星期天23:40自动导出Excel结束");
        return ;
    }

    public void AutoRecordToExcel1(String PATH){
        boolean flag=true;
        System.out.println("星期天23:40自动导出Excel开始");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(calendar.getTime());
        //导出数据到Excel表格
        String filename=PATH+date+".xlsx";
        Collection<User> users = userService.GetAll();
        EasyExcel.write(filename,User.class).sheet("模板").doWrite(users);
        System.out.println("星期天23:40自动导出Excel结束");
        return ;
    }
}
