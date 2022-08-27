package com.example.timeattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TimeAttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeAttendanceApplication.class, args);
    }

}
