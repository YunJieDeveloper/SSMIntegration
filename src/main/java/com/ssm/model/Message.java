package com.ssm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;
@Data
public class Message {
    //发送者
    public Long from;
    //发送者名称
    public String fromName;
    //接收者
    public Long to;
    //发送的文本
    public String text;
    //发送日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    public Date date;
}
