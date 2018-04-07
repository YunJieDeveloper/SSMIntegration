package com.ssm.model;

import lombok.Data;

import java.util.Date;
/***
 * @Date 2017/12/26
 *@Description 实体类User
 * @author zhanghesheng
 * */
@Data
public class User {
    private Long id;
    private String name;
    private String depart;
    private Double salary;
}
