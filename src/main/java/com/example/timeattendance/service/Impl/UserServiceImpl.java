package com.example.timeattendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.timeattendance.mapper.AttendanceRankMapper;
import com.example.timeattendance.mapper.TimeRecordMapper;
import com.example.timeattendance.mapper.UserMapper;
import com.example.timeattendance.pojo.TimeRecord;
import com.example.timeattendance.pojo.User;
import com.example.timeattendance.service.UserService;
import com.example.timeattendance.utils.MyEmail;
import com.example.timeattendance.utils.R;
import com.example.timeattendance.utils.TimeUtils;
import com.example.timeattendance.utils.code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TimeRecordMapper timeRecordMapper;
    @Autowired
    AttendanceRankMapper attendanceRankMapper;

    @Override
    public R SelectOneById(String studentid){
        boolean flag=true;
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getStudentId,studentid);
        User userdata = userMapper.selectOne(lqw);
        R r = new R();
        r.setData(userdata);
        if(userdata==null){
            System.out.println("学号"+studentid+"不存在");
            r.setMsg("学号"+studentid+"不存在");
            r.setCode(code.SELECT_ERR);
            flag=false;
        }else{
            System.out.println("学号"+studentid+"查询成功");
            r.setMsg("学号"+studentid+"查询成功");
            r.setCode(code.SELECT_OK);
        }
        r.setFlag(flag);
        return r;
    }

    @Override
    public R LoginByid(String studentid) {
        R r=new R();
        User userdata;
        boolean flag=true;
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getStudentId,studentid);
//        lqw.select(User::getStatus,User::getId);
        userdata = userMapper.selectOne(lqw);
        r.setData(userdata);
        System.out.println(userdata);
        if(userdata==null) {
            System.out.println("学号"+studentid+"不存在");
            r.setMsg("学号"+studentid+"不存在");
            flag=false;
            r.setCode(code.LOGIN_ERR_2);
        }
        else if(userdata.getStatus()==0)
        {
            userdata.setStatus(1);
            userdata.setStartTime(TimeUtils.GetNowtime());
            userMapper.updateById(userdata);
            System.out.println(studentid+"签到成功");

            r.setMsg(studentid+"签到成功");
            r.setCode(code.LOGIN_OK);
        }else{
            r.setMsg(studentid+"已签到");
            System.out.println(studentid+"已签到");
            r.setCode(code.LOGIN_ERR_1);
            flag=false;
        }
        r.setFlag(flag);
        return r;
    }

    //记录本次打卡的时间
    @Override
    public boolean AddTime(User user){
        boolean flag=true;
        double duration= Double.parseDouble(TimeUtils.TimeCalculate(user.getStartTime(),user.getEndTime()));
        user.setWeekTime(user.getWeekTime()+duration);
        user.setTotalTime(user.getTotalTime()+duration);
        userMapper.updateById(user);
        return flag;
    }
    //记录本次打卡的记录
    @Override
    public boolean RecordTime(User user,int status){
        boolean flag=true;
        TimeRecord timeRecord = new TimeRecord(user.getId(), user.getStartTime(), user.getEndTime()
                , Double.parseDouble(TimeUtils.TimeCalculate(user.getStartTime(), user.getEndTime())),status);
        int insert = timeRecordMapper.insert(timeRecord);
        if(insert==1){
            flag=true;
            System.out.println(user.getStudentId()+"打卡记录成功");
        }else{
            flag=false;
            System.out.println(user.getStudentId()+"打卡记录失败");
        }
        return flag;
    }
    @Override
    public R LogoutByid(String studentid){
        R r = new R();
        boolean flag=true;
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getStudentId,studentid);
        User user = userMapper.selectOne(lqw);
        r.setData(user);
//        System.out.println(user);
        if(user==null) {
            System.out.println("学号"+studentid+"不存在");
            r.setMsg("学号"+studentid+"不存在");
            r.setCode(code.LOGOUT_ERR_2);
            flag=false;
        }
        else if(user.getStatus()==1)
        {
            user.setStatus(0);
            user.setEndTime(TimeUtils.GetNowtime());
            userMapper.updateById(user);
            System.out.println(studentid+"签退成功");
            r.setMsg(studentid+"签退成功");
            r.setCode(code.LOGOUT_OK);
        }else{
            System.out.println(studentid+"未签到");
            r.setMsg(studentid+"未签到");
            r.setCode(code.LOGOUT_ERR_1);
            flag=false;
        }
        r.setFlag(flag);
        return r;
    }
    @Override
    public boolean LogoutByidandAddtime(String studentid) {
        boolean flag=true;
        R r=LogoutByid(studentid);
        User user =(User) r.getData();
        if(r.getMsg().equals(studentid+"签退成功")){
            flag=true;
            boolean a = AddTime(user);
            boolean b = RecordTime(user,1);
            System.out.println(user.getStudentId()+"记录打卡时间成功");
        }else{
            flag=false;
            System.out.println(user.getStudentId()+"记录打卡时间失败");
        }
        return flag;
    }
    @Override
    public boolean  LogoutAll(int status){
        //超时自动签退
        boolean flag=true;
        boolean a=true;
        boolean b=true;
        Collection<User> users = GetOnline();
        Long targetid;
        for (User user:users
             ) {
            MyEmail.SendEmail(user.getQq()+"qq.com","晚间签退通知","本次签退不计入总时长，下次记得签退");
            targetid=user.getId();
            User temp = (User) LogoutByid(user.getStudentId()).getData();
            if(status==1)a = AddTime(temp);
            b = RecordTime(temp, status);
            if(a&&b){
                flag=true;
                System.out.println("所有在线成员已成功签退");
                System.out.println("是否记录："+status);
            }else{
                flag=false;
            }
        }
        return flag;
    }
    @Override
    public Collection<User> GetOnline(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getStatus,1);
        List<User> users = userMapper.selectList(lqw);
        System.out.println("有"+ users.size()+"人在线");
        return users;
    }

    @Override
    public Collection<User> GetTopthree(String grade){
        Collection<User> users = attendanceRankMapper.GetTopthree(grade);
        System.out.println(users);
        return users;
    }
    @Override
    public Collection<User> SelectByGrade(String grade,String isonline){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.likeRight(User::getStudentId,grade);
        lqw.eq(isonline!=null,User::getStatus,isonline);//isonline为null的话就查全部
//        String gra="'"+grade+"%'";
        Collection<User> users = userMapper.selectList(lqw);

        return users;
    }

    @Override
    public boolean AddUser(User user){
        System.out.println(user);
        boolean flag=true;
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getStudentId,user.getStudentId());
        User user1 = userMapper.selectOne(lqw);

        if(user1==null)
        {
            int insert = userMapper.insert(user);
            if(insert==1)
            {
                System.out.println(user.getStudentId()+"添加成功");
                flag=true;
            }else{
                System.out.println(user.getStudentId()+"添加失败");
                flag=false;
            }
        }else{
            flag=false;
            System.out.println(user.getStudentId()+"已存在");
        }


        return flag;
    }
    @Override
    public Collection<User> GetAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println("查询所有人");
        return users;
    }

    @Override
    //查询我的打卡记录
    public Collection<TimeRecord> GetMyTimeRecord(String userid){
        LambdaQueryWrapper<TimeRecord> lqw = new LambdaQueryWrapper<TimeRecord>();
        lqw.eq(TimeRecord::getUserid,userid);
        lqw.orderByDesc(TimeRecord::getId);
        List<TimeRecord> mytimeRecords = timeRecordMapper.selectList(lqw);
        return mytimeRecords;
    }

    @Override
    //举报功能
    public R Report(String reportid,String reporterid){
        boolean flag=false;
        R r = new R();
        User user = (User) SelectOneById(reportid).getData();

        if(user!=null&&user.getStatus()==1){
            User user1 =(User) LogoutByid(user.getStudentId()).getData();
            TimeRecord timeRecord = new TimeRecord(user1.getId(), user1.getStartTime(), user1.getEndTime()
                    , Double.parseDouble(TimeUtils.TimeCalculate(user1.getStartTime(), user1.getEndTime())),2,reporterid);
            timeRecord.setReporterid(reporterid);
            timeRecord.setStatus(2);
            int i = timeRecordMapper.insert(timeRecord);
            if(i==1)
            {
                MyEmail.SendEmail(user.getQq()+"qq.com","举报通知","你被他人举报了,下次记得签退");
                System.out.println(reporterid+"举报"+reportid+"成功");
                r.setMsg(reporterid+"举报"+reportid+"成功");
                flag=true;
            }else{
                System.out.println(reporterid+"举报"+reportid+"失败");
                r.setMsg(reporterid+"举报"+reportid+"失败");
                flag=false;
            }
        }else if(user==null){
            System.out.println(reportid+"不存在");
            r.setMsg(reportid+"不存在");
            flag=false;
        }else if(user.getStatus()==0){
            System.out.println(reportid+"未签到");
            r.setMsg(reportid+"未签到");
            flag=false;
        }
        r.setFlag(flag);
        return r;
    }
}
