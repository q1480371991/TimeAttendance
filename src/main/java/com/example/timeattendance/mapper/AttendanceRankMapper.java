package com.example.timeattendance.mapper;

import com.example.timeattendance.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface AttendanceRankMapper {
//    order by weektime DESC
    @Select("select * from user where studentid like '${grade}%' order by weektime DESC limit 0,3")
    public Collection<User> GetTopthree(String grade);
}
