package com.example.timeattendance.controller;

import com.example.timeattendance.service.AutoJob;
import com.example.timeattendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExcelController {
    @Autowired
    AutoJob autoJob;
    @GetMapping("/excel")
    public void GetExcel(){
        autoJob.AutoRecordToExcel();
    }
}
