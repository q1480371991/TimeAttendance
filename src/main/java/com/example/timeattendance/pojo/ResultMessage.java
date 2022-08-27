package com.example.timeattendance.pojo;

import lombok.Data;

@Data
public class ResultMessage {
    private boolean SystemMsgFlag;
    private Object message;

    public ResultMessage(boolean systemMsgFlag, Object message) {
        this.SystemMsgFlag=systemMsgFlag;
        this.message= message;
    }
}
