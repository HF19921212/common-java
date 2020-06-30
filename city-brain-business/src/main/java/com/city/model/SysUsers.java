package com.city.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysUsers {
    private Short id;

    private String name;

    private String password;

    private String salt;

    private Date createTime;

    private Short creatorId;

    private String remark;

}