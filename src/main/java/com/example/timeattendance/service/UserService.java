package com.example.timeattendance.service;

import com.example.timeattendance.pojo.TimeRecord;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.utils.R;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

public interface UserService {
    public R SelectOneById(String studentd);
    public R LoginByid(String studentid);
    public boolean LogoutByidandAddtime(String studentid);
    public boolean LogoutAll(int status);
    public R LogoutByid(String studentid);
    public boolean RecordTime(User user,int status);
    public boolean AddTime(User user);
    public Collection<User> GetAll();
    public Collection<User> GetOnline();
    public Collection<User> GetTopthree(String garde);
    public Collection<User> SelectByGrade(String grade,String isonline);
    public Collection<TimeRecord> GetMyTimeRecord(String userid);
    public boolean AddUser(User user);
    public boolean Report(String studentid,String reportid);

}
