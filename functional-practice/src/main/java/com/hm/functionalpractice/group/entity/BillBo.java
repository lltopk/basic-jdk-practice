package com.hm.functionalpractice.group.entity;

import lombok.Data;

/**
* @Description: 
* @Author: hmly
* @project: basic-jdk-practice
* @Date: 2024/12/26 19:52 
*/
@Data
public class BillBo {
    private Long id;
    private Double price;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String state;
    private String zip;
    private Integer sex;
    private Integer age;
    private String name;
    //分组字段
    private String bornCity;
    //分组字段
    private String university;
    //分组字段
    private String workCity;



}
