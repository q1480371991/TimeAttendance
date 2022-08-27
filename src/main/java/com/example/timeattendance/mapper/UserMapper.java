package com.example.timeattendance.mapper;

import com.example.timeattendance.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User>{
    @Select("select * from  user where studentid like '${grade}%' ")
    public Collection<User> SelectByGrade(String grade);

    @Update("update user set status = 0 where id = #{id}")
    public void Report(String id);
}
