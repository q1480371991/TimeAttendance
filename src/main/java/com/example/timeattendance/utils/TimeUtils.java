package com.example.timeattendance.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    public static long DatetoMillisecond(String time){
        time+=".000";
        //日期字符串转LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        //这里LocalDateTime必须要先转成Instant，之后才能获取对应的时间毫秒数
        long millis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return millis;
    }
    public static String MillisecondtoDate(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(time);
        return date;
    }
    public static String toHourandMinute(long time){
        String result;
        result=""+time/1000/60/60+"小时"+time/1000/60%60+"分钟";
        return result;
    }
    public static String toHour(long time){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String result;
        result=""+decimalFormat.format((double)time/1000/60/60);
        return result;
    }
    public static String TimeCalculate(String starttime,String endtime){
        String result=toHour(DatetoMillisecond(endtime)-DatetoMillisecond(starttime));
        return result;
    }

    public static String GetNowtime(){
        //当前系统默认时区下的日期时间
        LocalDateTime now = LocalDateTime.now();
        //格式化
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return time;
    }

    public static String Gethour(String s){
        return s.substring(11,13);
    }

    public static boolean isreset(){
        boolean flage=true;
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(week==5&&hour==0)flage=true;
        else flage=false;
        return flage;
    }
    public static boolean istime() {
        boolean flage=true;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date nowTime = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            nowTime = df.parse(df.format(new Date()));
            beginTime = df.parse("07:00");
            endTime = df.parse("23:30");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
        } else {
            flage=false;
        }
        return  flage;
    }
}

