package com.example.timeattendance.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @ExcelIgnore
    private Integer Status;
    @ExcelProperty("名字")
    private String name;
    @TableField(value = "studentid")
    @ExcelProperty("学号")
    @JsonProperty("studentid")
    private String StudentId;
    @ExcelIgnore
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value ="starttime" )
    @ExcelIgnore
    private String StartTime;
    @ExcelIgnore
    @TableField(value = "endtime")
    private String EndTime;
    @TableField(value = "weektime")
    @ExcelProperty("本周打卡时间")
    private double WeekTime;
    @ExcelIgnore
    @TableField(value = "totaltime")
    private double TotalTime;
}
