package com.example.timeattendance.utils;


import com.example.timeattendance.pojo.Message;
import com.example.timeattendance.pojo.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.transform.Result;

public class MessageUtils {
    public static String getMessage(boolean SystemMsgFlag, Object message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultMessage resultMessage = new ResultMessage(SystemMsgFlag, message);

        String resdata = objectMapper.writeValueAsString(resultMessage);
//        System.out.println(resdata);
        return resdata;
    }
}
