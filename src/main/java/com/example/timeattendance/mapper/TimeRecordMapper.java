package com.example.timeattendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.timeattendance.pojo.TimeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TimeRecordMapper extends BaseMapper<TimeRecord> {

}
