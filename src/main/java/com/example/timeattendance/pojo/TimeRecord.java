package com.example.timeattendance.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

@TableName(value = "timerecord")
public class TimeRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "userid")
    private long userid;
    @TableField(value = "startTime")
    private String StartTime;
    @TableField(value = "endTime")
    private String EndTime;
    private double duration;
    @TableField(value = "recordstatus")
    private int status;
    @TableField(value = "reporter")
    private String reporterid;
    public TimeRecord(long userid, String StartTime, String EndTime,double duration){
        this.userid=userid;
        this.StartTime=StartTime;
        this.EndTime=EndTime;
        this.duration=duration;
    }
    public TimeRecord(long userid, String StartTime, String EndTime,double duration,int status){
        this.userid=userid;
        this.StartTime=StartTime;
        this.EndTime=EndTime;
        this.duration=duration;
        this.status=status;
    }
    public TimeRecord(long userid, String StartTime, String EndTime,double duration,int status,String reporterid){
        this.userid=userid;
        this.StartTime=StartTime;
        this.EndTime=EndTime;
        this.duration=duration;
        this.status=status;
        this.reporterid=reporterid;
    }
    public TimeRecord(){
    }
}
